#!/bin/bash

LOG_PATH="/home/ec2-user/app"
mkdir -p $LOG_PATH
touch $LOG_PATH/deploy.log $LOG_PATH/deploy_err.log
chmod 666 $LOG_PATH/deploy.log $LOG_PATH/deploy_err.log

# 1. 새 JAR 파일 탐색
JAR_PATH=$(find /home/ec2-user/app -name "*.jar" | head -n 1)
echo "> 새로 배포할 파일: $JAR_PATH" >> $LOG_PATH/deploy.log

if [ -z "$JAR_PATH" ]; then
    echo "> 배포할 새로운 .jar 파일을 찾을 수 없습니다." >> $LOG_PATH/deploy.log
    exit 1
fi

# 2. 기존 서비스 종료
echo "> 기존 my-pocket.service 중지" >> $LOG_PATH/deploy.log
if systemctl is-active --quiet my-pocket.service; then
    sudo systemctl stop my-pocket.service
    sleep 5
else
    echo "> my-pocket.service가 실행 중이 아니므로 중지하지 않음" >> $LOG_PATH/deploy.log
fi

# 3. 새 애플리케이션 실행
echo "> 새 애플리케이션 실행" >> $LOG_PATH/deploy.log
nohup java -jar $JAR_PATH >> $LOG_PATH/deploy.log 2>> $LOG_PATH/deploy_err.log &

# 4. Health Check
echo "> Health check 시작" >> $LOG_PATH/deploy.log
for RETRY_COUNT in {1..10}
do
  echo "> #${RETRY_COUNT} 번째 Health check 시도..." >> $LOG_PATH/deploy.log
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/actuator/health)

  if [ "$RESPONSE_CODE" -eq 200 ]; then
    echo "> Health check 성공" >> $LOG_PATH/deploy.log
    exit 0
  fi
  sleep 5
done

echo "> Health check 실패" >> $LOG_PATH/deploy.log
exit 1
