package com.project.aegis.serviceImpl;

import com.project.aegis.model.Transaksi;
import com.project.aegis.model.TransaksiDetail;
import com.project.aegis.repository.TransaksiDetailRepository;
import com.project.aegis.repository.TransaksiRepository;
import com.project.aegis.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LaporanServiceImpl {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private TransaksiDetailRepository transaksiDetailRepository;

    @Autowired
    private StringUtil stringUtil;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<Resource> createLaporanTransaksi(String startTime, String endTime) {
        String filePath = "../laporan/test1.xlsx";
        String fileName = "test1";

        Date startDate = stringToDate(startTime, "yyyy-MM-dd HH:mm:ss");
        Date endDate = stringToDate(endTime, "yyyy-MM-dd HH:mm:ss");


        List<Transaksi> transaksiList = transaksiRepository.findByRange(new java.sql.Timestamp(startDate.getTime()), new java.sql.Timestamp(endDate.getTime()));
        if (!transaksiList.isEmpty()) {
            try {
                Workbook workbook = new HSSFWorkbook();

                // Create a new sheet
                Sheet sheet = workbook.createSheet("Transaksi");

                // Create a row and add some cells
                Row headerRow = sheet.createRow(5);
                headerRow.createCell(0).setCellValue("ID");
                headerRow.createCell(1).setCellValue("Name");
                headerRow.createCell(2).setCellValue("Qty");
                headerRow.createCell(3).setCellValue("Price");
                headerRow.createCell(4).setCellValue("Created At");
                headerRow.createCell(5).setCellValue("Created By");

                transaksiList.forEach(transaksi -> {
                    List<TransaksiDetail> transaksiDetailList = transaksiDetailRepository.findByIdTransaksi(transaksi.getId());
                    AtomicInteger index = new AtomicInteger(1);
                    if (!transaksiDetailList.isEmpty()) {
                        transaksiDetailList.forEach(transaksiDetail -> {
                            int rowNum = 5 + index.get();
                            Row row = sheet.createRow(rowNum);
                            row.createCell(0).setCellValue(transaksiDetail.getIdTransaksi());
                            row.createCell(1).setCellValue(transaksiDetail.getName());
                            row.createCell(2).setCellValue(transaksiDetail.getQty());
                            row.createCell(3).setCellValue(transaksiDetail.getPrice());
                            row.createCell(4).setCellValue(transaksi.getCreatedAt());
                            row.createCell(5).setCellValue(transaksi.getCreatedBy());

                            index.incrementAndGet();
                        });
                    }
                });


                // Write the workbook to a file
                FileOutputStream outputStream = new FileOutputStream("../laporan/1.xlsx");
                workbook.write(outputStream);
                outputStream.close();
                workbook.close();
            } catch (Exception e) {
                logger.error(stringUtil.getError(e));
            }

        }

        Resource zipFileResource = loadFile(filePath, fileName);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/xlsx"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(zipFileResource);
    }

    public Resource loadFile(String filePath, String filename) {
        try {
            Path file = Paths.get(filePath).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {

        }

        return null;
    }

    public static Date stringToDate(String dateString, String format) {
        if (dateString != null && !dateString.equalsIgnoreCase("null") && !dateString.isEmpty() && format != null && !format.isEmpty()) {
            try {
                Date date = (new SimpleDateFormat(format)).parse(dateString);
                return date;
            } catch (ParseException var3) {
                ParseException e = var3;
                return null;
            }
        } else {
            return null;
        }
    }
}
