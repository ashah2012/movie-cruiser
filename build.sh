

cd movie-cruiser-auth-service
source ./env-variable.sh
mvn clean package -DskipTests=true
docker build -t user-auth-app .
cd ..
cd movie-cruiser-backend
source ./env-variable.sh
mvn clean package -DskipTests=true
docker build -t movie-app .
cd ..
