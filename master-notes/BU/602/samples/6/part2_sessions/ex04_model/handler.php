<?php
    
function open($path, $name) {
    //perform some action here
    return true;
}

function read($sessionId) { 
    global $db;

    $sql = "SELECT session_data FROM session where session_id =" . 
              $db->quote($sessionId);
    $result = $db->query($sql);
    $data = $result->fetchColumn();
    $result->closeCursor();

    return $data;
}

function write($sessionId, $data) { 
    global $db;

    echo "<p>In write...<br/>";
    pr_dump($sessionId);
    pr_dump($data);
   
    $sql = "INSERT INTO session SET session_id =" . 
        $db->quote($sessionId) . ", session_data =" . 
        $db->quote($data) . 
        " ON DUPLICATE KEY UPDATE session_data =" . 
        $db->quote($data);
    
    $db->query($sql);

    return true;
    
}

function close() {
    $sessionId = session_id();
    //perform some action here
    return true;
}

function destroy($sessionId) {
    global $db;

    $sql = "DELETE FROM session WHERE session_id =" . 
        $db->quote($sessionId); 
    
    $db->query($sql);

    setcookie(session_name(), "", time() - 3600);
    return true;
}

/* the max lifetime of the session which is an integer detailing the number of seconds that the lifetime spans.*/

function garbage($lifetime) {
    global $db;

    $sql = "DELETE FROM session WHERE session_lastaccesstime < DATE_SUB(NOW(), INTERVAL " . $lifetime . " SECOND)";
    $db->query($sql);
    return true;
}

function pr_dump($var) { 
    print '<pre>'; print_r($var); print '</pre>'; 
}

?>