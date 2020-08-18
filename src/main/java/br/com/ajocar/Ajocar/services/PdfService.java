package br.com.ajocar.Ajocar.services;

import br.com.ajocar.Ajocar.model.Car;
import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.model.Product;
import br.com.ajocar.Ajocar.model.ServiceOrder;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

@Service
public class PdfService {
    private final String PATH_SYSTEM = "src/main/resources/static/img/";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font redFontBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD, BaseColor.RED);
    private static Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.BLUE);
    private static Font italicFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLDITALIC);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private static Font smallNormal = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);

    public ByteArrayInputStream makePdf(Client client, ServiceOrder serviceOrder, Car car, List<Product> products) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();


        try {
            PdfWriter e = PdfWriter.getInstance(document,out);
            document.open();
//            makeClientData(car,client,document);
   //         makeOrderData(serviceOrder,document);
            test(document,client,car,serviceOrder);

            document.close();


        } catch (DocumentException e) {
            //todo
            System.out.println("Error in make the pdf file");
        }

        return new ByteArrayInputStream(out.toByteArray());

    }


    private void test(Document document,Client client,Car car,ServiceOrder service){
        try {
            Paragraph preface = new Paragraph();
            // We add one empty line
            addEmptyLine(preface, 1);
            // Lets write a big header
            addImage(preface,"src/main/resources/static/img/ajocar_logo.png",150,50,Element.ALIGN_LEFT,100);
            Paragraph title = new Paragraph("DADOS DO CLIENTE", catFont);
            title.setAlignment(Element.ALIGN_CENTER);
            preface.add(title);
            addImage(preface,"src/main/resources/static/img/line.png",190,14,Element.ALIGN_CENTER,100);

            String clientPara = "Cliente: ".concat(client.getName());
            Paragraph cliientP = new Paragraph(clientPara,smallBold);
            cliientP.setAlignment(Element.ALIGN_LEFT);
            preface.add(cliientP);
            String endP = "End: ".concat(client.getAddress().toLine());
            Paragraph endParagraph = new Paragraph(endP,smallNormal);
            cliientP.setAlignment(Element.ALIGN_LEFT);
            preface.add(endParagraph);
            String tel = "Telefone: ".concat(client.getTel());
            Paragraph telP = new Paragraph(tel,smallNormal);
            cliientP.setAlignment(Element.ALIGN_LEFT);
            preface.add(telP);
            String vei = "Veiculo: ".concat(car.toLine());
            Paragraph veiP = new Paragraph(vei,smallNormal);
            cliientP.setAlignment(Element.ALIGN_LEFT);
            preface.add(veiP);
            String km = "Km Atual: ".concat(String.valueOf(car.getKmActually()));
            Paragraph kmP = new Paragraph(km,smallNormal);
            cliientP.setAlignment(Element.ALIGN_LEFT);
            preface.add(kmP);
            Paragraph titleTwo = new Paragraph("ORDEM DE SERVIÇO", catFont);
            titleTwo.setAlignment(Element.ALIGN_CENTER);
            preface.add(titleTwo);
            addImage(preface,"src/main/resources/static/img/line.png",190,14,Element.ALIGN_CENTER,100);
            makeOrderData(service,preface);



            document.add(preface);
        }catch (DocumentException e){
            System.out.println("shit");
        }
    }

    private void addImage(Paragraph paragraph,String path,int width,int height, int align,float porcentage) {
        try {
            Image image = Image.getInstance(path);
            image.setAlignment(align);
            image.scaleAbsolute(width, height);
            image.setWidthPercentage(porcentage);
            paragraph.add(image);

        } catch (DocumentException e) {
            System.out.println("shit image");

        } catch (IOException  | NullPointerException e ) {
            System.out.println("shit imgae");

        }
    }

    private void makeOrderData(ServiceOrder serviceOrder, Paragraph paragraph) {
        try {
            PdfPTable table = new PdfPTable(2);
            float[] columnWidths = {1f, 3f};
            table.setWidths(columnWidths);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            table.setWidthPercentage(100);
            PdfPCell cell1 = new PdfPCell(new Paragraph("  QNTD"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("  Descrição de produtos"));
            table.addCell(cell1);
            table.addCell(cell2);

            for(Product product : serviceOrder.getProducts()){
                PdfPCell cell = new PdfPCell(new Paragraph("  ".concat(String.valueOf(product.getQuantity()))));
                table.addCell(cell);
                PdfPCell cellName = new PdfPCell(new Paragraph("  ".concat(product.getName())));
                table.addCell(cellName);
            }

            paragraph.add(table);
            Chunk chunk = new Chunk("Observação: ", smallBold);
            Chunk chunkTwo = new Chunk(serviceOrder.getServiceObservation(), smallNormal);
            Paragraph pObs = new Paragraph();
            pObs.add(chunk);
            pObs.add(chunkTwo);
            paragraph.add(pObs);

            Chunk chunkTotal = new Chunk("TOTAL DE PEÇAS: ", smallBold);
            String totalP = String.valueOf(serviceOrder.getPiecesTotal());
            Chunk chunkTotalN = new Chunk(totalP, redFont);
            Paragraph total = new Paragraph();
            total.setAlignment(Element.ALIGN_RIGHT);
            total.add(chunkTotal);
            total.add(chunkTotalN);
            paragraph.add(total);


            Chunk chunkWork = new Chunk("MÃO DE OBRA: ", smallBold);
            String workP = String.valueOf(serviceOrder.getServiceCost());
            Chunk chunkWorkN = new Chunk(workP, blueFont);
            Paragraph work = new Paragraph();
            work.setAlignment(Element.ALIGN_RIGHT);
            work.add(chunkWork);
            work.add(chunkWorkN);
            paragraph.add(work);

            Chunk chunkTotalWork = new Chunk("TOTAL A PAGAR: ", smallBold);
            String workTotalP = String.valueOf(serviceOrder.getTotalWork());
            Chunk chunkWorkTotalN = new Chunk(workTotalP, blueFont);
            Paragraph workTotal = new Paragraph();
            workTotal.setAlignment(Element.ALIGN_RIGHT);
            workTotal.add(chunkTotalWork);
            workTotal.add(chunkWorkTotalN);
            paragraph.add(workTotal);

            Paragraph thanks = new Paragraph("Agradecemos pela preferência.",italicFont);
            thanks.setAlignment(Element.ALIGN_RIGHT);
            paragraph.add(thanks);

            Chunk chunkOneTec = new Chunk("T", redFont);
            chunkOneTec.setUnderline(0.1f, -2f);
            Chunk chunkTwoTec = new Chunk("écnico: ", smallBold);
            chunkTwoTec.setUnderline(0.1f, -2f);
            Chunk chunkTreeTec = new Chunk("Anivaldo Dias de Oliveira", italicFont);
            chunkTreeTec.setUnderline(0.1f, -2f);
            Paragraph anivaldo =  new Paragraph();
            anivaldo.setAlignment(Element.ALIGN_LEFT);
            anivaldo.add(chunkOneTec);
            anivaldo.add(chunkTwoTec);
            anivaldo.add(chunkTreeTec);

            paragraph.add(anivaldo);

            Chunk msg1 = new Chunk("D", redFontBold);
            Chunk msg2 = new Chunk("eus ", smallBold);
            Chunk msg3 = new Chunk(" S", redFontBold);
            Chunk msg4 = new Chunk("empre", smallBold);
            Chunk msg5 = new Chunk(" F", redFontBold);
            Chunk msg6 = new Chunk("iel", smallBold);

            Paragraph msg =  new Paragraph();
            msg.setAlignment(Element.ALIGN_CENTER);
            msg.add(msg1);
            msg.add(msg2);
            msg.add(msg3);
            msg.add(msg4);
            msg.add(msg5);
            msg.add(msg6);
            paragraph.add(msg);


            paragraph.add(new Paragraph(
                    "      "));
            Paragraph telTec =  new Paragraph();
            telTec.add(new Chunk(getImage("WPP.png",45,20,10),0,-9,true));
            telTec.add(new Chunk(getImage("nextel.jpg",50,20,2),0,-9,true));
            Chunk chunTelTec = new Chunk(" (11) 94737-5548 Nivaldo Dias - Consultor Técnico.",smallBold);
            telTec.add(chunTelTec);

            paragraph.add(telTec);



        }catch (Exception e ){
            System.out.println("shit table");
        }


    }
    private Image getImage(String img,int width,int height, float percent){
        try {
            Image image = Image.getInstance(PATH_SYSTEM.concat(img));
            image.setAlignment(Element.ALIGN_LEFT);

            image.scaleAbsolute(width, height);
            image.scalePercent(percent);
            return image;

        } catch (DocumentException e) {
            System.out.println("shit image");

        } catch (IOException  | NullPointerException e ) {
            System.out.println("shit imgae");

        }
        return null;
    }

    private void makeClientData(Car car, Client client,Document document) {
       try {
           Paragraph preface = new Paragraph();
           addEmptyLine(preface,7);
           Paragraph elements = new Paragraph();
           elements.add("Cliente: ".concat(client.getName()));
           Paragraph tel = new Paragraph();
           tel.add("Telefone: ".concat(client.getTel()));
           Paragraph end = new Paragraph();
           end.add("End: ".concat(client.getAddress().getAddress()));
           Paragraph vei = new Paragraph();
           vei.add("Veiculo: ".concat(car.getVehicle()));
           Paragraph km = new Paragraph();
           km.add("KM Atual: ".concat(String.valueOf(car.getKmActually())));

           preface.add(elements);
           preface.add(tel);
           preface.add(end);
           preface.add(vei);
           preface.add(km);
           document.add(preface);
       }catch (DocumentException e) {
           e.printStackTrace();
       }
    }



    private void addEmptyLine(Paragraph paragraph, int number){

        for (int i=0; i< number; i++){
            paragraph.add(new Paragraph(""));
        }
    }
}
