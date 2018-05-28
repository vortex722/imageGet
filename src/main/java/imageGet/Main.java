package imageGet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class Main {
	  public static void main(String[] args) {
	        try {
				run();
	        	//GCSList();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
	  }

//
//	  public static void GCSList() {
//		  String BUCKET = "k3c-analytics-datalake-us";
//		  String DIR = "work/sumida/";
//
//		  //初期化
//		  Storage storage = StorageOptions.getDefaultInstance().getService();
//
//		  Bucket bucket = storage.get(BUCKET);
//
//		  // 特定のディレクトリのみに絞る
//		  Storage.BlobListOption option = Storage.BlobListOption.prefix(DIR);
//
//		  Page<Blob> blobs = bucket.list(option);
//		  Iterator<Blob> blobiterator = (Iterator<Blob>) blobs.iterateAll();
//
//		  while(blobiterator.hasNext()) {
//			  System.out.println(blobiterator.next().getName());
//		  }
//
//	  }
//

	  public static void run() {

			try{
				//inputファイル読み込み
				FileReader fr = new FileReader("data/input.csv");
				BufferedReader br = new BufferedReader(fr);


				//読み込んだファイルを１行ずつ処理する
	            String line;
	            StringTokenizer token;
	            int line_number = 1;
	            String imagePath = "data/";
	            while((line = br.readLine()) != null) {
	            	//区切り文字で分割
	            	token = new StringTokenizer(line,",");
	            	int counter = 0;
	            	while (token.hasMoreTokens()) {
	            		System.out.println(line_number + "-" + counter);
	                    //最初のカラムのみとるようにしてみる
	            		if(counter>=1) {
	            			break;
	            		}

	            		String imageUrl = token.nextToken();
	            		NumberFormat nf = NumberFormat.getInstance();
	            		nf.setGroupingUsed(false);
	            		nf.setMinimumIntegerDigits(7);
	            		String filePath = imagePath + "img_" + nf.format(line_number) + ".jpg";

	            		//画像ファイル取得
	            		SampleJsoup.getImageByUrl(imageUrl, filePath);

	            		//1秒待つ
	            		try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}

	            		counter++;
	                }
	            	line_number ++ ;
	            }


			} catch(IOException ex){
				ex.printStackTrace();
			}
		}
}
