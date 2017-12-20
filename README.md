# Q
Q is a promise library for Java. It implements promises ("deferred objects") similar to the ones found in ECMAScript, Angular.js's $q, and [Kris Kowal's Q](https://github.com/kriskowal/q). 

##Usage
When you want a method to return data that does not exist yet, create a `Promise` for that data and return the promise instead. 
The `Q` class acts as the controller for a promise object. Only the `Q` instance which created a promise may resolve or reject it. 

```
public static Promise<Integer> getInt() {
	final Q<Integer> futureInt = Q.defer();
	new Thread("some long operation") {
		public void run() {
			if (something) {
				futureInt.resolve(5);
			} else {
				futureInt.reject("this is not the integer you are looking for");
			}
		}
	}.start();
	return futureInt.promise;
}
```

The receiver of a promise may then block on it, or specify a callback function to be executed when data is available or the promise is rejected. 

```
public static void main(String... args) {
	Promise<Integer> futureInt = getInt();
	//do some other operations while we wait
	int i = futureInt.get();
	System.out.println("i is " + i);
}
```

In the last example, a `PromiseRejectionException` would have been thrown at the call of `futureInt.get()` if the promise had been rejected. Instead of blocking synchronously, we could have specified a callback to be executed on the resolving thread: 


```
public static void main(String... args) {
	Promise<Integer> futureInt = getInt().then(new PromiseCallback() {
		public void resolved(int i) {
			System.out.println("i is " + i);
		}
		public void rejected(String err, Throwable cause) {
			System.err.println("Could not get int because of " + err);
			if (cause != null) cause.printStackTrace();
		}
	});
	//do some other operations while we wait
}
```