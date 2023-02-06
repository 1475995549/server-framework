#jasypt加密
#!/bin/bash
mvn jasypt:encrypt-value -Djasypt.encryptor.password="zhihan" -Djasypt.plugin.value=$1

#解密
# mvn jasypt:decrypt-value -Djasypt.encryptor.password="zhihan" -Djasypt.plugin.value="Im59LuW3DLmQ4iPK3WTuMfZfTPSkokNVAM2UKf7Sd94MVsP0hliSmVJ5a/d7fPQE"

#加密配置文件内 DEC(123456) 中的明文 转为ENC(nmuRjZ7F8jXowALOuJWalYwRb95Jxg2pJTrA5l3pfhRDD451H+BqhpraNrT5B18e)
# mvn jasypt:encrypt -Djasypt.encryptor.password="zhihan" -Djasypt.plugin.path="file:/Users/suzui/code/IdeaProjects/server-demo/demo-web/src/main/resources/application.yml" //绝对路径

#最好将密钥或私钥作为环境变量参数在执行应用的启动命令时传入，而不是放在配置文件中
# -Djasypt.encryptor.password="zhihan"