<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vote Register</title>
    <script>
        function addOption() {
            const optionList = document.getElementById("optionList");
            const optionDiv = document.createElement("div");
            optionDiv.innerHTML = `
                <input type="text" name="options" placeholder="옵션 입력">
                <button type="button" onclick="removeOption(this)">-</button>
            `;
            optionList.appendChild(optionDiv);
        }

        function removeOption(button) {
            const optionDiv = button.parentElement;
            optionDiv.remove();
        }
    </script>
</head>
<body>
<h1>새 투표 작성</h1>
<form action="${pageContext.request.contextPath}/Vote/create" method="post">
    <div>
        <label for="title">제목:</label>
        <input type="text" name="title" id="title" placeholder="투표 제목 입력" required>
    </div>
    <div id="optionList">
        <h3>투표 항목 입력</h3>
        <div>
            <input type="text" name="options" placeholder="옵션 입력">
            <button type="button" onclick="removeOption(this)">-</button>
        </div>
    </div>
    <button type="button" onclick="addOption()">+</button>
    <div>
        <button type="reset">리셋</button>
        <button type="submit">완료</button>
    </div>
</form>
</body>
</html>