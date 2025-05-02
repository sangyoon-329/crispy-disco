<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- ul 태그 내에 1~10 까지의 숫자를 반복해서 출력함. -->
<%!
Integer[] generateArray(int begin, int end){
	List<Integer> list = new ArrayList<>(); 
	for(int i=begin; i<=end; i++){
		list.add(i);
	}
	Integer[] array = new Integer[list.size()];
	return list.toArray(array);
}
%>
<ul>
<%
	Integer[] array = generateArray(1, 10);
	String lis = Arrays.stream(array)
				.map(n -> String.format("<li>%d</li>",n))
				.collect(Collectors.joining("\n"));
%>
<%=lis %>
</ul>
<hr>
<ul>
<%
	for(int i=1; i<=10; i++){ 
		out.print(String.format("<li>%d</li>", i));
	}
%>
</ul>
<hr>
<ul>
<%for(int i=1; i<=10; i++){ %>
<li><%=i %></li>
<%} %>
</ul>
</body>
</html>