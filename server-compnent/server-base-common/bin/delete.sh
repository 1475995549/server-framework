#删除发布版本时插件生成的backup文件
#!/bin/bash
rm -rf pom.xml.*Backup;
rm -rf */pom.xml.*Backup;
rm -rf release.properties;

