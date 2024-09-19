#!/bin/bash

# Usage: ./deploy.sh <app_name> <username> <profile>
# Example: ./deploy.sh mvp-springboot-jdk17 azureadmin dev

set -e

# Parameters
APP_NAME=$1
USERNAME=$2
PROFILE=$3
STORAGE_ACCOUNT=$4
STORAGE_CONTAINER=$5
STORAGE_ACCESS_KEY=$6

# Validate parameters
if [ -z "$APP_NAME" ] || [ -z "$USERNAME" ] || [ -z "$PROFILE" ]; then
  echo "Error: Missing required parameters."
  echo "Usage: $0 <app_name> <username> <profile>"
  exit 1
fi

# Log file
LOG_FILE="/home/${USERNAME}/${APP_NAME}.log"

echo "Starting download process for application: ${APP_NAME}"
az storage blob download \
  --account-name tiurefcomsa \
  --container-name tiu-ref-com-arti-con \
  --file ./${APP_NAME}.jar --name ${APP_NAME}.jar \
  --auth-mode key --account-key ${STORAGE_ACCESS_KEY}
echo "Download completed. Artifact filename is ${APP_NAME}.jar..."

echo "Starting deployment process for application: ${APP_NAME}"
echo "Using user: ${USERNAME}"
echo "Spring profile: ${PROFILE}"

# Kill any existing process running with the JAR name
echo "Attempting to stop any running instances of ${APP_NAME}.jar..."
sudo pkill -f "${APP_NAME}.jar" || true
echo "Process termination completed."

# Start the new process
echo "Starting the new instance of ${APP_NAME}.jar with profile ${PROFILE}..."
nohup java -jar /home/${USERNAME}/${APP_NAME}.jar \
    --spring.profiles.active=${PROFILE} >> ${LOG_FILE} 2>&1 &
echo "New process started. Logs are being written to ${LOG_FILE}."

echo "Deployment completed successfully."
