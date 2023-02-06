#正式打包release
#!/bin/bash
mvn release:prepare
mvn release:perform
