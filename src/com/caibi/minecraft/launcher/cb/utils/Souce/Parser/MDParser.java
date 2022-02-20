package com.caibi.minecraft.launcher.cb.utils.Souce.Parser;

import com.caibi.minecraft.launcher.cb.utils.Souce.SourceDownloader;

import com.caibi.minecraft.launcher.cb.utils.Utils;
import org.json.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MDParser {
    static JSONObject versionsData = new JSONObject(VersionParser.parse("versions list url"));

    public static void parse(String id){
        String data = readFile(id);
        System.out.println(data);
    }

    static String readFile(String id){
        File file = new File("Resource\\Launcher\\Versions_Data\\"+id+".json");
        String data = "{}";
        try {
            if (!file.exists()){
                Utils.Log("没有找到文件");
                if (!(new File(file.getParent())).exists()){
                    if ((new File(file.getParent())).mkdirs()){
                        Utils.Log("成功创建: "+(new File(file.getParent())));
                    }
                }
                if (file.createNewFile()){
                    Utils.Log("成功创建: Launcher\\Versions_Data\\"+id+".json");
                    SourceDownloader.download(versionsData.getString(id), "Launcher\\Versions_Data\\"+id+".json", false, false);
                    Utils.Log("成功下载:Launcher\\Versions_Data\\"+id+".json");
                }
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                data = scanner.nextLine();
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
