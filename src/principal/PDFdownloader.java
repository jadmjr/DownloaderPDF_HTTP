package principal;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PDFdownloader {
	// ENDEREÇO DO SITE
	public static String URL = "http://www.dominiopublico.gov.br/download/texto/";
	// PASTS DE DESTINO
	public static String pastaDeDestino = "C:\\Users\\";
	// DADO DA LISTA
	static String caminhoArquivo = "lista/";
	static String nomeArquivo = "lista.txt";
	static String path = caminhoArquivo + nomeArquivo;
	//
	static String linha = "";

	public static void main(String[] args) {
		lerArquivo();
	}

	// REALIZA LEITURA DE ARQUIVO .TXT
	public static void lerArquivo() {
		BufferedReader buffRead;
		try {

			buffRead = new BufferedReader(new FileReader(path));
			String linha = "";

			while (true) {
				if (linha != null) {
					if (linha.contains(".pdf")) {
						baixaArquivo(linha);
					}
				} else
					break;
				linha = buffRead.readLine();
			}
			buffRead.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			baixaArquivo(linha);
		}
	}

	public static void baixaArquivo(String filename) {
		try {
			saveUrl(pastaDeDestino + filename, URL + filename);
		} catch (MalformedURLException e) {
			//
		} catch (IOException e) {
			//
		}

	}

	public static void saveUrl(String filename, String urlString) throws MalformedURLException, IOException {
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		try {
			// URL url = new URL(urlString);

			URL u = new URL(urlString);
			HttpURLConnection huc = (HttpURLConnection) u.openConnection();

			huc.setRequestMethod("GET");
			huc.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");

			String myCookies = "JSESSIONID=2CC42D5DB313062A67587C441FED768F;__utma=16876716.2068962685.1541093230.1541093230.1541093230.1";
			huc.setRequestProperty("Cookie", myCookies);

			huc.connect();

			in = new BufferedInputStream(huc.getInputStream());

			fout = new FileOutputStream(filename);

			byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				System.out.println("baixando o arquivo" + filename + " ...");
				fout.write(data, 0, count);
			}
		} finally {
			if (in != null)
				in.close();
			if (fout != null)
				fout.close();
		}
	}
}