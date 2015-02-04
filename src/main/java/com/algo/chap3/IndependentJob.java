package com.algo.chap3;

/**
 * 独立任务最优调度问题的解法
 * 
 * 当完成k个作业，设机器A花费了x时间，机器B所花费时间的最小值肯定是x的一个函数
 * 设F[k][x]表示机器B所花费时间的最小值。则F[k][x]=Min{ F[k-1][x]+b[k], F[k-1][x-a[k]] }
 * 其中F[k-1][x]+b[k]表示第k个作业由机器B来处理（完成k-1个作业时机器A花费的时间仍是x）
 * F[k-1][x-a[k]]表示第k个作业由机器A处理（完成k-1个作业时机器A花费的时间是x-a[k]）
 * 
 * 创建时间: 2011-4-12
 * @author Destiny
 *
 */
public class IndependentJob
{
	/**
	 * 保存作业的数目
	 */
	private int numOfJobs;
	
	/**
	 * 分别保存每个Job使用A或者B时的耗时信息
	 */
	private int[] aTimes;
	private int[] bTimes;
	
	/**
	 * 保存作业数从1-n是的最优时间，time[n - 1]即表示最后的结果
	 */
	private int[] times;
	
	/**
	 * 保存作业1-n是被那一个完成
	 * 比如results[0]='A' 即表示作业1是由A完成的
	 */
	private String[] results;
	
	public IndependentJob(int num, int[] a, int[] b)
	{
		this.numOfJobs = num;
		this.aTimes = a;
		this.bTimes = b;
		
		//对部分数组进行初始化
		this.times = new int[num];
		this.results = new String[num];
	}
	
	/**
	 * 
	 */
	public void process()
	{
		//首先为最后的结果给定一个最大阈值
		for(int i = 0; i < this.times.length; i++)
			this.times[i] = 999;
		
		//首先对传入的A、B进行分析
		int sumA, sumB;
		sumA = sumB = 0;
		for (int i = 0; i < this.aTimes.length; i++)
		{
			sumA += aTimes[i];
			sumB += bTimes[i];
		}
		int sum = 1 + ((sumA > sumB) ? sumB : sumA);
		
		/**
		 * 保存A可能用的时间
		 */
		int[][] timeA = new int[this.numOfJobs][sum];
		
		/**
		 * 保存B可能用的时间
		 */
		int[][] timeB = new int[this.numOfJobs][sum];
		
		/**
		 * 记录两者共需的时间
		 */
		int[][] timeMax = new int[this.numOfJobs][sum];
		
		/**
		 * 记录是由哪一个完成
		 */
		char[][] who = new char[this.numOfJobs][sum];
		
		/**
		 * 用来保存临时的结果集
		 */
		char[] tempResult = new char[this.numOfJobs];
		
		//开始计算第一个任务的相关值，记录在以上四个二维数组的第0行
		//始终以A的需要工时为考量
		for(int i = 0; i <= this.aTimes[0]; i++)
		{
			//无论如何，timeA中记录的就是循环变量的值
			timeA[0][i] = i;
			if(i < aTimes[0])	//意味着A不能完成，时间不够，需要由B来完成
			{
				timeB[0][i] = this.bTimes[0];
				who[0][i] = 'B';
			}
			else	//	意味着A能完成，时间刚好等于第一任务在A上完成的时间，即aTimes[0]
			{
				timeB[0][i] = 0;
				who[0][i] = 'A';
			}
			//计算timeMax中相应的元素
			timeMax[0][i] = Math.max(timeA[0][i], timeB[0][i]);
		}
		
		//计算result
		if(aTimes[0] < bTimes[0])
		{
			this.times[0] = aTimes[0];
			tempResult[0] = 'A';
		}
		else
		{
			this.times[0] = bTimes[0];
			tempResult[0] = 'B';
		}
		this.results[0] = new String(tempResult);
		
		//开始计算第2个任务到第n个任务的时间信息
		for(int k = 1; k < this.numOfJobs; k++)
		{
			 //tempSum记录完成前k项任务机器A最多需要的时间
			 //即全部由A完成需要的时间，亦即机器A所有可能的取值范围
			int tempSum = 0;
			for(int j = 0; j <= k; j++)
				tempSum += aTimes[j];
			
			//计算出所有可能的点对(timeA, timeB)，并取值timeMax
			for(int i = 0; i < tempSum; i++)
			{
				//无论如何，timeA中记录的就是循环变量的值
				timeA[k][i] = i;
				
				//意味着只能由B来完成，其中aTimes[k]是第k个任务对于A所需要的时间
				if(i < aTimes[k])
				{
					timeB[k][i] = timeB[k - 1][i] + bTimes[k];
					who[k][i] = 'B';
				}
				else
				{
					//意味着让B来做所需的时间更少
					if((timeB[k - 1][i] + bTimes[k]) <= timeB[k - 1][i - aTimes[k]])
					{
						timeB[k][i] = timeB[k - 1][i] + bTimes[k];
						who[k][i] = 'B';
					}
					//意味着让A来做所需要的时间更少
					else
					{
						timeB[k][i] = timeB[k - 1][i - aTimes[k]];
						who[k][i] = 'A';
					}
				}
				//A和B中花费时间较大的那个为总花费时间
				timeMax[k][i] = Math.max(timeA[k][i], timeB[k][i]);
			}
				
			//处理数组tempSum后面的值。机器A时间全部设为最大，此时机器B则无需花费时间。
            for (int i = tempSum + 1; i < sumA; i++)
            {
                timeA[k][i] = tempSum;
                timeB[k][i] = 0;
            }
            
            //完成第k项任务后，在timeMax所有可能值中，选取最小值即最优值。
            int flag = 0;//记录最优值所在的位置i值，同时也是机器A所花费的时间。
            for (int i = 0; i <= tempSum; i++)
            {
                if (timeMax[k][i] > 0 && timeMax[k][i] < times[k])
                {
                	times[k] = timeMax[k][i];
                    flag = i;
                }
            }
            
            //------------------------------------------以下的内容不太懂
          //用来回溯所用机器的顺序，需要注意的是机器A所花费的时间和数组的下标一样，因此可用做标记。
            //最后一项任务的回溯路径已在示意图中标出，便于理解。
            //http://hi.baidu.com/liongg/blog/item/63d6a9ec19454c2262d09f01.html
            tempResult[k] = who[k][flag];
            for (int i = k; i > 0 && flag > 0; i--)
            {
                //如果是机器A完成第i项任务，则机器A花费的时间减去a[i]就是完成前i-1项任务的时间。
                //同时在who中可以确定前一项任务的机器，因为机器A花费的时间可用做位置标记。
                if (tempResult[i] == 'A')
                {
                    flag -= aTimes[i];
                    tempResult[i - 1] = who[i - 1][flag];
                }
                if (tempResult[i] == 'B')
                {
                	tempResult[i - 1] = who[i - 1][flag];
                }
            }
            results[k] = new String(tempResult);
		}
	}

	public int[] getTimes()
	{
		return times;
	}

	public String[] getResults()
	{
		return results;
	}
}
