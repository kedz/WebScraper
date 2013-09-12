package edu.columbia.cs.article.classifier;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 7/5/13
 * Time: 8:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClassifierCommandLine {

    public static void printMenu() {

        System.out.println("+-----------+------------------------------------------+");
        System.out.println("+        Article Text Classifier Trainer v0            +");
        System.out.println("+-----------+------------------------------------------+");
        System.out.println("|-  MENU   -+- report:  Display collection statistics -|");
        System.out.println("|-----------+--                                        |");
        System.out.println("|           +- add:     Add a new newspaper           -|");
        System.out.println("|           +--                                        |");
        System.out.println("|           +- label:   Label examples fo a newspaper -|");
        System.out.println("|           +--                                        |");
        System.out.println("|           +- exit:    Exit program                  -|");
        System.out.println("+-----------+------------------------------------------+");


    }

    public static void printPrompt() {
        System.out.print("$> ");
    }

    public static void addNewspaper(Scanner scanner, ClassifierTrainer classifierTrainer) {


        String newspaperTitle = "";
        String newspaperUrlString = "";
        URL newspaperUrl = null;
        long waitTime = -1;

        while(newspaperTitle.equals("")) {
            System.out.print("Enter newspaper title             $> ");
            newspaperTitle = scanner.nextLine();

        }

        while (newspaperUrlString.equals("")) {
            System.out.print("Enter newspaper url               $> ");
            newspaperUrlString = scanner.nextLine();
            try {

                newspaperUrl = new URL(newspaperUrlString);
            } catch (MalformedURLException murl) {
                murl.printStackTrace();
                System.exit(-1);
            }
        }

        while(waitTime<0) {
            System.out.print("Enter home page wait time(millis) $> ");
            waitTime = scanner.nextLong();
        }

        System.out.println("Adding newspaper:");
        System.out.println("\tTITLE     : "+newspaperTitle);
        System.out.println("\tURL       : "+newspaperUrl);
        System.out.println("\tWAIT TIME :"+waitTime);
        NewspaperMeta newspaperMeta = new NewspaperMeta.Builder(newspaperTitle, newspaperUrl).waitTime(waitTime).build();

        classifierTrainer.getNewspaperMetaSet().addNewspaperMeta(newspaperMeta);


    }


    public static void printReport(ClassifierTrainer classifierTrainer) {

        NewspaperMetaSet newspaperMetaSet = classifierTrainer.getNewspaperMetaSet();
        System.out.println("Article Classifier Site Report");

        int index = 1;
        for(NewspaperMeta newspaperMeta : newspaperMetaSet.getNewspaperMetas()) {

            System.out.printf("| %3d | %30s | %30s | %010d |\n",
                    index++,
                    newspaperMeta.getPublicationTitle(),
                    newspaperMeta.getPublicationUrl(),
                    newspaperMeta.getSitesLabeled());

        }

        System.out.println();

    }

    public static void startLabeling(Scanner scanner, ClassifierTrainer classifierTrainer) {

        NewspaperMetaSet newspaperMetaSet = classifierTrainer.getNewspaperMetaSet();
        System.out.println("Enter the number of the Newspaper to start labeling pages: ");

        int index = 1;

        for(NewspaperMeta newspaperMeta : newspaperMetaSet.getNewspaperMetas()) {

            System.out.printf("| %3d | %30s | %30s |\n",
                    index++,
                    newspaperMeta.getPublicationTitle(),
                    newspaperMeta.getPublicationUrl());

        }
        int labelIndex = 0;
        while (labelIndex <= 0 || labelIndex > newspaperMetaSet.size()) {
            printPrompt();
            labelIndex = scanner.nextInt();

        }

        NewspaperMeta newspaperMeta = newspaperMetaSet.getNewspaperMetas().get(labelIndex-1);

        classifierTrainer.labelNewspaper(newspaperMeta);

    }




    public static void main(String[] args) {




        Scanner scanIn = new Scanner(System.in);
        ClassifierTrainer classifierTrainer = new ClassifierTrainer();

        printMenu();
        printPrompt();


        String line = "";
        while(!line.equals("exit")) {
            line = scanIn.nextLine();

            if (line.equals("menu")) {
                printMenu();
                printPrompt();

            } else if (line.equals("add")) {
                addNewspaper(scanIn, classifierTrainer);

            } else if (line.equals("report")) {

                printReport(classifierTrainer);
                printPrompt();

            } else if (line.equals("label")) {
                if (classifierTrainer.getNewspaperMetaSet().size()>0)
                    startLabeling(scanIn,classifierTrainer);
                else {
                    System.out.println("No newspapers added!");
                    printPrompt();
                }

            } else if (line.equals("exit")) {

                System.out.print("Saving...");
                try {
                    classifierTrainer.writeMetaXml();
                } catch (TransformerConfigurationException tce) {
                    tce.printStackTrace();
                } catch (TransformerException te) {
                    te.printStackTrace();
                } catch (ParserConfigurationException pce) {
                    pce.printStackTrace();
                }

                System.out.println("\tfinished! Good bye.");

            } else  {
                printPrompt();
            }




        }


        scanIn.close();
    }


}
