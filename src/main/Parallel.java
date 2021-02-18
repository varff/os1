import java.util.;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;


public class Parallel {

	private static class MyThread extends Thread{


		private int sum;
		private ListListInteger subMatrix;

		MyThread(ListListInteger subMatrix){this.subMatrix = subMatrix;}

		@Override
		public void run() {
			for (ListInteger row subMatrix) {
				int maxValue = Integer.MIN_VALUE;
				for(int value  row)
					maxValue = Math.max(maxValue,value);
				sum+=maxValue;
			}
		}
		public int getSum() {
			return sum;
		}

	}

	static int sumParallel(ListListInteger matrix, int threadsNum) throws InterruptedException{

		int arraysTotal = matrix.size();

		int div = arraysTotalthreadsNum, rem = arraysTotal%threadsNum;

		if(div==0){
			threadsNum = rem;
		}

		MyThread[] threads = new MyThread[threadsNum];

		int startIndex = 0;

		for(int i = 0;ithreadsNum;i++){
			int arraysNum = div; 3
			if(rem0){
				arraysNum+=1;
				rem--;
			}
			threads[i] = new MyThread(matrix.subList(startIndex,startIndex+arraysNum));
			startIndex += arraysNum;
			threads[i].start();
		}

		int sum = 0;

		for(int i = 0;ithreadsNum;i++){
			threads[i].join();
			sum+=threads[i].getSum();
		}


		return sum;
	}

	static ListListInteger generateMatrix(int rows, int cols){

		ListListInteger matrix = new ArrayList();
		Random r = new Random();
		int low = -10;
		int high = 10;

		for(int i = 0;irows;i++){
			ListInteger row = new ArrayList();
			for(int j = 0;jcols;j++)
				row.add(r.nextInt(high-low) + low);
			matrix.add(row);
		}
		return matrix;
	}
	static int sumParallelStream(ListListInteger matrix, int threadsNum) throws InterruptedException,
			ExecutionException {
		ForkJoinPool customThreadPool = new ForkJoinPool(threadsNum);
		return customThreadPool.submit(() - matrix.parallelStream().map
				(list - list.stream().max(Integercompare).get()).collect(Collectors.toList()).
				stream().reduce(0,Integersum)).get();

	}

	public static void main(String[] args){
		ListListInteger matrix = generateMatrix(3,3);
		try{
			long startTime = System.currentTimeMillis();


			System.out.println(sumParallel(matrix,1));
			long estimatedTime = System.currentTimeMillis() - startTime;
			System.out.println(estimatedTime+n);

			startTime = System.currentTimeMillis();
			System.out.println(sumParallel(matrix,2));
			estimatedTime = System.currentTimeMillis() - startTime;
			System.out.println(estimatedTime+n);
			startTime = System.currentTimeMillis();
			System.out.println(sumParallel(matrix,7));
			estimatedTime = System.currentTimeMillis() - startTime;
			System.out.println(estimatedTime+n);

			System.out.println(sumParallelStream(matrix,5));
		}
		catch(Exception e){}
	}
}