
# **Parallel and Asynchronous Programming**

### **Need for Parallel Programming**
With the growing popularity of mordern multicore machines, we as developers need to use patterns that allows us to write code which maximizes the use of multiple cores and inturn enables the code to run faster. The way in this may be achieved is by using Parallel Programming.

Example- ParallelStream

### **Need for Asynchronous Programming**

With the prevalance of mordern Microservice based or Distributed Architectures blocking calls often impact the latency of the application. Asynchronous Programming Concepts may be used to eliminate blocking calls.

Example- CompletableFuture

### **Comparsion of Concurrency and Parallelism**

**`Concurrency:`** Concurrency merely refers to more than one tasks running at the same time. Incase of a single core machine this means that multiple tasks may run in interleaved fashion on the same CPU core. 
  * This means Concurrency may be achieved in Single core as well as multi-core machine.
  * Concurrency is achieved using Threads. Inter-thread-communication is achieved using "Shared Objects" and "Messaging Queues". 
  * The pattern of using Threads to achieve parallelism suffers from Race Condition and Deadlocks. These side effects may be countered using Synchonized Blocks or Synchronized Methods , ReentrantLocks, Semaphores, Concurrent Collections, Conditional Objects Etc. These are readily available within java.

**`Parallelism:`** Parallelism is a concept  more than one tasks running at the same time making efficient use of a multicore machine. 
   * Parallelism talks about running more than one task in parallel on a machine that has more than one core, so by defination Parallelism can only be achieved on a multi-core machine.
   * Implementing Parallelism involves the below steps:
     * `Fork:` Decompose the task into subtasks and Execute the subtasks across multiple cores.
     * `Join:` Join the results of the subtask.
   * Within Java Parallism may achieved in two ways:
     * Implement the Fork Join Framework.
     * Parallel Stream.

### **Thread API**

Threads were introduced in Java 1 in 1996. Threads provides us a way to blocking tasks to run in the background. It allows developers to write asynchronous (non-blocking)  style of code.
  * **Limitations:** Threads suffers from a couple of limitations:
    * `Threads are Complex to use:` To use threads we need to explicitly create them, run them and then join them so that may be quite complex to use for inexperienced programmers.
    * `Threads can be quite expensive:` Since each thread has it's own runtime stack, memory registers, etc, they may become quite expensive to use.

    Example: `parallelism-executor-service ProductService.getProductById_Parallel_With_Threads(productId)`

        ProductInfoServiceRunnable productInfoServiceRunnable = new ProductInfoServiceRunnable(productInfoService, productId);
        ReviewSummaryServiceRunnable reviewSummaryServiceRunnable = new ReviewSummaryServiceRunnable(reviewSummaryService, productId);

        Thread productInfoServiceThread = new Thread(productInfoServiceRunnable);
        Thread reviewSummaryServiceThread = new Thread(reviewSummaryServiceRunnable);

        productInfoServiceThread.start();
        reviewSummaryServiceThread.start();

        productInfoServiceThread.join();
        reviewSummaryServiceThread.join();


### **Thread Pool**

A Thread Pool is basically as a group of pre-created threads which are readily availble for use.
The number of Threads a thread pool is called the size of the Thread Pool.
The size of the Thread pool depends upon the nature of the task and is also related to the number of cores the machine has:
  * For **`CPU Intensive Task`** the thread pool size is recommended to be equal to the number of CPU Cores.
  * For **`I/O Intensive Task`** the size is is recommended to be greater than the number of CPU Cores.
  * **Advantages:**
    * `Easy to Use:` With Thread Pools the Threads are precreated, so we don't need to manually create, start and join threads.

### **ExecutorService**

[Baeldung ExecutorService](https://www.baeldung.com/java-executor-service-tutorial)

ExecutorService was released as part of Java 5 in 2004. It's basically as **`Asynchronous Task Execution Engine`** that provides a way to asynchronously execute tasks and provides results in a relatively simple way as compared to using Threads.

* **Components of Executor Service** The implementation of an ExecutorService has 3 main components:
    * `WorkQueue:` Tasks are submitted here
    * `CompletionQueue:` The completed tasks are placed here
    * `ThreadPool:` This is a precreated group of Threads.
* An ExecutorService basically takes tasks from the work Queue and executes it in one of the free threads in the thread pool.
* The results of completed tasks can be accessed using futures.

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        
        Callable<ProductInfo> productInfoCallable = () -> productInfoService.getProductInfoByProductId(productId);
        Callable<ReviewSummary> reviewSummaryCallable = () -> reviewSummaryService.getReviewSummaryByProductId(productId);

        Future<ProductInfo> productInfoFuture = executorService.submit(productInfoCallable);
        Future<ReviewSummary> reviewSummaryFuture = executorService.submit(reviewSummaryCallable);

        ProductInfo productInfo = productInfoFuture.get();
        ReviewSummary reviewSummary = reviewSummaryFuture.get(1, TimeUnit.SECONDS);

        executorService.shutdown();

* **Limitations**
    * Future.get() methods are blocking in nature.
    * Muliple Futures can't be combined.
* ExecutorService is used to achieve **`task based parallelism.`**


## **Fork Join Framework** 
[Udemy Video](https://www.udemy.com/course/parallel-and-asynchronous-programming-in-modern-java/learn/lecture/21440344#questions)

* The Fork Join Framework uses Fork join Pool to achieve data parallelism.
* **Data Parallelism:** Fork-Join framework is designed to achieve Data Parallelism. Data Parallelism is a concept where a given task is recursively split into subtasks until it reaches the least possible size. For example- Suppose we have a task that transform the elements of a string list to upper case. To achieve data parallelism, this task would be split into subtasks where each tasks would be applying the uppercase transformation on each element of the list and the result of each subtasks are combined as a list and the final result is is returned. This means if there are 100 elements in the list 100 subtasks would be created. So data parallelism is achieved the divide and conquer approach.
* **`Fork Join Task:`** The Fork Join Task is a special type that is accepted by the Fork Join Queue. It has the ability to split into subtask and the results are joined after execution. 
  * Fork Join Task represents a part of the data and the computation that needs to be performed on that part of the data. For example- If we are performing upper case transformation on the elements of a string list, a Fork Join Task may represent an element of the list along with the uppercase operation.
  * There are mainly 2 types of Fork Join Tasks that's accepted by the Fork Join Pool.
    * `RecursiveTask:` Recursive Task is used when we need to return a value.
    * `RecursiveAction:` Recursive Action is used when we don't need the task to return a value.
* **Components Fork Join Pool:** The Fork Join Pool involves the following elements.
  * `Shared Work Queue:` When client submits a tasks it's placed in the Shared Work Queue.
  * `Worker Threads:` The Worker Threads polls the Shared Work Queue
  * `Doubly Ended Work Queue:` Each Worker Thread has it's own Doubly Ended Work Queue also called Deck.
* **Internal Working of Fork Join Pool**
  * The client submits a task of type Fork Join Task to the Fork Join Pool, this task is placed in the Shared Work Queue.
  * This task is then polled by one of the Worker Threads(Say Worker A) and placed in it's Deck. 
  * This task may be further split into sub tasks and placed in it's deck.
  * **`Work Stealing:`** The other Worker Threads may steal these subtasks from the Worker A's deck and place them in their own respective decks and execute them, while the Worker A is busy executing one of the subtasks. This process is called Work Stealing and is an efficient method to distribute tasks across other worker threads.
  * Once all the subtasks have finished execution, the results of each of the subtasks are combined & the final result is returned to client.

* Example- parallelism-fork-join ForkJoinNameTransformTask

        public class ForkJoinNameTransformTask extends RecursiveTask<List<String>> {
            private List<String> nameList;

            public ForkJoinNameTransformTask(List<String> nameList) {
                this.nameList = nameList;
            }

            public List<String> transformAndGetResult() {
                ForkJoinPool forkJoinPool = new ForkJoinPool();
                ForkJoinNameTransformTask forkJoinNameTransformTask = new ForkJoinNameTransformTask(nameList);
                List<String> resultList=forkJoinPool.invoke(forkJoinNameTransformTask);
                return resultList;
            }

            @Override
            protected List<String> compute() {
                if (nameList.size() <= 1) {
                    //return nameList.stream().map(name -> convertToUpperCaseAndAddLength(name)).collect(Collectors.toList());
                    List<String> resultList = new ArrayList<>();
                    nameList.forEach(name-> resultList.add(convertToUpperCaseAndAddLength(name)));
                    return resultList;
                }
                int mid = nameList.size() / 2;
                List<String> leftList=nameList.subList(0, mid);
                ForkJoinTask<List<String>> leftPartForkJoinTask = new ForkJoinNameTransformTask(leftList).fork(); //The computation of the left part of the list has been forked
                nameList = nameList.subList(mid, nameList.size()); // The right part of the nameList is stored in the nameList itself
                List<String> rightResult = compute();
                List<String> leftResult = leftPartForkJoinTask.join();
                leftResult.addAll(rightResult);
                return leftResult;
            }

            public String convertToUpperCaseAndAddLength(String name) {
                delay(1000);
                return name.toUpperCase() + "-" + name.length();
            }

        }

## **Parallel Streams**

[Oracle Blog: Considerations for using Parallel Streams](https://blogs.oracle.com/javamagazine/post/java-parallel-streams-performance-benchmark#:~:text=A%20sequential%20stream%20is%20executed,CPU%20cores%20in%20a%20computer.)

A parallel stream is split into multiple substreams that may be processed in parallel by multiple instances of the stream pipeline being executed by multiple threads, and their intermediate results are combined to create the final result.

A parallel stream can be created only directly on a collection by invoking the `Collection.parallelStream() `method.

### **Stream Execution Modes:** 
The sequential or parallel mode of an existing stream can be modified by calling the `BaseStream.sequential()` and `BaseStream.parallel()` intermediate operations, respectively. So this methods may be used to get quick baselines of our stream pipeline and then later use the `Collection.parallelStream()` method to create the parallel stream directly from the collections.

.parallel() takes precedence over .sequential(). So if both execution modes are mentioned in the pipeline then the stream is executed in parallel.

A stream is executed sequentially or in parallel depending on the execution mode of the stream on which the terminal operation is initiated.

*Parallel streams utilize the `fork/join framework` for executing parallel tasks.*

* With ParallelStreams the total number of tasks that can run in parallel is equal to the number of cores in the CPU







