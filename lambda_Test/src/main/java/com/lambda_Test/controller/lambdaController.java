package com.lambda_Test.controller;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@FunctionalInterface
interface Mymy {
	int f(int y, int zx);
}

@FunctionalInterface
interface MyFunction {
	int cal(int x, int y);
}

@RestController
public class lambdaController {

	@GetMapping("/")
	public String test(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Enumeration<?> e = session.getAttributeNames();
		String sessionAttribute = "";
		while(e.hasMoreElements()) {
			sessionAttribute = e.nextElement().toString();
			System.out.println("session의 Attribute =====> " + sessionAttribute);
		}
		
		MyFunction myFunction = (x,y) -> {
			return (x+y);
		};
		System.out.println("test ===> " + myFunction.cal(1, 2));
		
		// arrays에 넣기
		int[] arr = new int[10];
		Arrays.setAll(arr, (i) -> {
			return (int) (Math.random() *100 +1);
		});
		System.out.println("arrays ===> " + Arrays.toString(arr));
		
		// map에 넣기 - 출력 시 반드시 applyAsInt 사용해야함
		Map<String, UnaryOperator> maptest = new HashMap<>();
		maptest.put("1", (i) -> "test");
		System.out.println("mapTest ===> " + maptest.get("1").apply(maptest));
		
		
		return "완료";
	}
	
	@GetMapping("/functionTest")
	public int functionTest() {
		// Function<T, R>
		// 추상 메서드 - R apply(T t)
		// T 타입으로 입력 받고 R 타입으로 결과 리턴
		Function<String, Integer> test = (String str) -> {
			return str.length();
		};
		
		int result = test.apply("안녕,Tester");
		System.out.println("test ===> " + result);
		return result;
	}
	
	@GetMapping("/consumerTest")
	public void consumerTest() {
		// Consumer<T>
		// 추상 메서드 - void accept(T t)
		// 단일 인수만 받으며 결과를 리턴하지 않아도 될 때 사용
		// T 타입으로 입력 받고 리턴하지 않음
        Consumer<String> test = (String age) -> {
            System.out.println("Age: " + age);
        };

        test.accept("25"); // Output: Age: 25
    }
	
	@GetMapping("/supplierTest")
	public String supplierTest() {
		// Supplier<T>
		// 추상 메서드 - T get()
		// 인수를 입력 받지 않고 T 타입으로 결과 리턴
		Supplier<String> test = () -> {
			return "테스트입니다.";
		};
		
		String result = test.get();
		System.out.println("test ====> " + result);
		return result;
	}
	
	@GetMapping("predicateTest")
	public boolean predicateTest() {
		// Predicate<T>
		// 추상 메서드 - boolean test(T t)
		// T 타입으로 하나의 인수를 입력 받아 테스트하고 Boolean형으로 결과 리턴 
		Predicate<String> test = (String str) -> {
			return str.isEmpty();
		};
		
		boolean result1 = test.test("");
		boolean result2 = test.test("test");
		System.out.println("test ====> " + result1); // true
		System.out.println("test ====> " + result2); // false
		return result1;
	}
	
	@GetMapping("bifunctionTest")
	public void bifunctionTest() {
		// BiFunction<T, U, R>
		// 추상 메서드 - R apply(T t, U u)
		// T와 U 타입으로 두개의 인수를 입력 받고 R 타입으로 결과 리턴
		BiFunction<Integer, Integer, String> test = (t, u) -> {
			return t+u.toString();
		};
		
		String result = test.apply(58, 60);
		
		System.out.println("test ====> " + result);
	}
	
	@GetMapping("binaryOperatorTest")
	public void binaryOperatorTest() {
		// BinaryOperator<T>
		// 추상 메서드 - T apply(T t1, T t2)
		// T 타입의 두 인수를 입력 받고 T 타입으로 결과 반환
		BinaryOperator<String> test = (a, b) -> {
			return a+b;
		};
		
		String result = test.apply("test", "입니다");
		System.out.println("test1 ====> " + result);
	}
	
	@GetMapping("unaryOperatorTest")
	public void unaryOperatorTest() {
		// UnaryOperator<T>
		// 추상 메서드 - T apply(T t)
		// T 타입으로 입력 받고 동일한 유형으로 결과 반환
		UnaryOperator<String> test = t -> {
			return t.toUpperCase();
		};
		
		String result = test.apply("upper and lower");
		System.out.println("test ====> " + result);
	}

}
