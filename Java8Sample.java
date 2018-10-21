import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Java8Sample {

	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6);

		// Use and evolution of lambdas
		// external iterator 1
		for (int i = 0; i < values.size(); i++) {
			System.out.println(values.get(i));
		}

		// external iterator 2
		// since java 5
		for (int val : values) {
			System.out.println(val);
		}

		// internal iterator 1
		values.forEach(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				System.out.println(t);
			}
		});

		values.forEach((Integer a) -> {
			System.out.println(a);
		});

		values.forEach((a) -> System.out.println(a));

		// method reference
		values.forEach(System.out::println);

		Consumer<Integer> methodRef1 = System.out::println;
		values.forEach(methodRef1);
		// Stream - map - reduce
		System.out.println("Map reduce operation - " + values.stream().map(e -> e * 2).reduce(0, (c, e) -> c + e));

		//use of predicate
		totalMethod(values,(a)-> a%2==0);//will add only even
		totalMethod(values, a -> true);//will add all numbers
		totalMethod(values,(a)-> a%2!=0);//will add only odd
		
	}

	public static Integer totalMethod(List<Integer> vals, Predicate<Integer> criteria) {
		Integer total = 0;
//Old fashion of code
		/*for (Integer integer : vals) {
			if(criteria.test(integer)) {
				total += integer;
			}
		}*/
			
//With stream and lambdas
		total =	vals.stream().filter(criteria).reduce(0,(c,e)->c+e);
		System.out.println("After total operation : "+total);
		return total;

	}
	
	/**
	 * Method returns first even number in a list which is > 3 and doubles before returning
	 * @param List<Integer> vals
	 * @return
	 */
	public static Integer doubleOfFirstEven(List<Integer> vals) {
	Integer num = 0;
	vals.stream().filter(a-> a>3)
				 .filter(a-> a%2==0)
				 .map(e -> e*2)//you can add this line to make square of the number found
				 .findFirst()//finds only first number
				 .orElse(0);//provides default value if number not found according to prescibed criteria
	return num;
	}
}
