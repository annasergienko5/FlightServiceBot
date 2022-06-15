package team.devim.Ability.FileWork;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Document;
import team.devim.Ability.Constants;

import java.io.*;
import java.net.URL;

public class FileDownloader {
    public File downloadFile(Document doc) throws IOException {
        String file_id = doc.getFileId();
        URL url = new URL("https://api.telegram.org/bot" + Constants.BOT_TOKEN + "/getFile?file_id=" + file_id);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String res = in.readLine();
        JSONObject jresult = new JSONObject(res);
        JSONObject path = jresult.getJSONObject("result");
        String file_path = path.getString("file_path");
        String downoloadUrl = String.format("https://api.telegram.org/file/bot%s/%s", Constants.BOT_TOKEN, file_path);
        InputStream is = new URL(downoloadUrl).openStream();
        File localFile = new File(file_path);
        FileUtils.copyInputStreamToFile(is, localFile);
        return localFile;
    }
}