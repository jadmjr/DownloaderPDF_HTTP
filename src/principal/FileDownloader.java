package principal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public final class FileDownloader {

	private FileDownloader() {
	}

	public static void main(String args[]) throws IOException {
		download("http://www.dominiopublico.gov.br/download/texto/me002722.pdf", new File("sample.pdf"));
	}

	public static void download(final String url, final File destination) throws IOException {

		URLConnection connection = null;
		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.5; en-US; rv:1.9.0.13) Gecko/2009073021 Firefox/3.0.13");
		connection.setConnectTimeout(60000);
		connection.setReadTimeout(60000);
		connection.addRequestProperty("User-Agent", "Mozilla/5.0");
		connection = new URL(url).openConnection();	

		final FileOutputStream output = new FileOutputStream(destination, false);
		final byte[] buffer = new byte[2048];
		int read;
		final InputStream input = connection.getInputStream();
		while ((read = input.read(buffer)) > -1)
			output.write(buffer, 0, read);
		output.flush();
		output.close();
		input.close();
	}
}