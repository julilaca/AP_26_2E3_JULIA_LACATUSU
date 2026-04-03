<!DOCTYPE html>
<html>
<head>
    <title>Movies</title>
    <style>
    </style>
</head>
<body>
    <h2>Movies Report</h2>
    <table>
        <tr>
            <th>Title</th>
            <th>Genre</th>
            <th>Score</th>
        </tr>
        <#list movies as movie>
        <tr>
            <td>${movie.title}</td>
            <td>${movie.genre}</td>
            <td>${movie.score}</td>
        </tr>
        </#list>
    </table>
</body>
</html>