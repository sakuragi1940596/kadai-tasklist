<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<label for="content">タスク</label><br />
<input type="text" name="content" value="${task.content}"/>
<br /><br />
<%-- 下記の記述により、CERF対策が可能となる --%>
<input type="hidden" name="_token" value="${_token}"/>
<button type="submit">作成</button>