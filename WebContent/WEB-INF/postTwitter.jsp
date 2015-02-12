<h1>Welcome ${twitter.screenName} (${twitter.id})</h1>	
    <form action="./post" method="post">
        <textarea cols="80" rows="2" name="text"></textarea>
        <br/>
        <input type="file" name="file">
        <input type="submit" name="post" value="update"/>
        
    </form>
    <a href="./logout">logout</a>