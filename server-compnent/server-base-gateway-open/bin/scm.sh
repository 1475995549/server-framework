#正式打包scm版本管理
#!/bin/bash
mvn scm:update
mvn scm:checkin -Dmessage=$1
