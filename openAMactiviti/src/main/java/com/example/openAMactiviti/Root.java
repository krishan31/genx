package com.example.openAMactiviti;

import java.util.List;

public class Root{
 public List<Result> result;
 public List<Result> getResult() {
	return result;
}
public void setResult(List<Result> result) {
	this.result = result;
}
public int getResultCount() {
	return resultCount;
}
public void setResultCount(int resultCount) {
	this.resultCount = resultCount;
}
public Object getPagedResultsCookie() {
	return pagedResultsCookie;
}
public void setPagedResultsCookie(Object pagedResultsCookie) {
	this.pagedResultsCookie = pagedResultsCookie;
}
public String getTotalPagedResultsPolicy() {
	return totalPagedResultsPolicy;
}
public void setTotalPagedResultsPolicy(String totalPagedResultsPolicy) {
	this.totalPagedResultsPolicy = totalPagedResultsPolicy;
}
public int getTotalPagedResults() {
	return totalPagedResults;
}
public void setTotalPagedResults(int totalPagedResults) {
	this.totalPagedResults = totalPagedResults;
}
public int getRemainingPagedResults() {
	return remainingPagedResults;
}
public void setRemainingPagedResults(int remainingPagedResults) {
	this.remainingPagedResults = remainingPagedResults;
}
public int resultCount;
 public Object pagedResultsCookie;
 public String totalPagedResultsPolicy;
 public int totalPagedResults;
 public int remainingPagedResults;
}


