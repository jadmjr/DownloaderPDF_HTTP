package principal;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ExtracaoDireta {

	public static void main(String[] args) {
		System.out.println("Abrindo Conexão");
		URL url;
		try {
			url = new URL("http://www.dominiopublico.gov.br/download/texto/me002722.pdf");
			
			///
			HttpURLConnection huc =  (HttpURLConnection)  url.openConnection();
			huc.setRequestMethod("GET"); 
			huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.0.13) Gecko/2009073021 Firefox/3.0.13");
			String myCookies = "cookie_name_1=cookie_value_1;cokoie_name_2=cookie_value_2";
			huc.setRequestProperty("Cookie", myCookies);
			huc.connect();          

			InputStream in = new BufferedInputStream(huc.getInputStream());
			///

			//InputStream in = url.openStream();

			String nomeArquivo = url.getFile().split("/")[url.getFile().split("/").length - 1];
			FileOutputStream fos = new FileOutputStream(new File(nomeArquivo));

			System.out.println("Lendo da fonte e gravando no arquivo...");

			int length = -1;

			byte[] buffer = new byte[1024];// buffer for portion of data from connection

			while ((length = in.read(buffer)) > -1) {
				fos.write(buffer, 0, length);
			}

			fos.close();

			in.close();

			System.out.println("Arquivo Baixado!");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
