package by.com.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//Код работает, но файлы new_name.json, new_name1.json нужно переименовывать на старое имя [name.json, name1.json],
// чтобы не выводился пустой массив[].
public class Main {
    public static Logger Logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Logger.trace("Start trace.");
        Logger.info("Application started.");

        Run run = new Run();

        Properties properties = run.asProperties("configuration.properties");
        Logger.info("Загрузка свойств");
        String path = properties.getProperty("path");
        String suffix = properties.getProperty("suffix");
        String[] strings = properties.getProperty("fileNames").split(",");

        List<String> extended = new ArrayList<>();
        List<String> namesWithoutExtend = new ArrayList<>();
        List<File> renamedFiles = new ArrayList<>();

        for (String s : strings) {
            namesWithoutExtend.add(s.split("\\.")[0]);
            extended.add("." + s.split("\\.")[1]);
        }
        try {
            File file;
            int count = 0;
            for (String nameFile : strings) {
                file = new File(path + nameFile);
                if (file.isFile()) {
                    File newFile = new File(path + suffix + "_" + namesWithoutExtend.get(count) + extended.get(count));
                    if (file.renameTo(newFile)) {
                        renamedFiles.add(newFile);
                        count++;
                    }
                }
            }
            Logger.info("Файлы переименованы");
        } catch (Exception e) {
            // LOGGER.error("Приложение аварийно завершилось");
        }
        System.out.println(renamedFiles);
        Logger.info("Application closed.");
        Logger.trace("End trace");
    }
}