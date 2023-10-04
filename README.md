# StackDrive.Headless

Проект запуска StackDrive в headless режиме

## Версия

Актуальный релиз __1.13.1__

## Сборка из исходников

   gradle build
    
## Развёртывание окружения на Linux

### Установка Java, Graphviz, Git и Nginx

        sudo yum -y install java-1.8.0-openjdk-devel
        sudo yum -y install graphviz
        sudo yum -y install git
        sudo yum -y install nginx

## Развёртывание StackDrive.Healess на Linux

### Создаем домашнюю папку приложения

        sudo mkdir /opt/stackdrive-headless
        
### Закидываем в /opt/stackdrive-headless бинарник
        
        cp ./stackdrive-headless.jar /opt/stackdrive-headless/stackdrive-headless.jar
        
### Добавляем executable bit для stackdrive-headless.jar
        
        chmod +x /opt/stackdrive-headless/stackdrive-headless.jar
        
### В /etc/systemd/system закидываем [stackdrive-headless.service]

        sudo cp ./stackdrive-headless.service /etc/systemd/system

### Меняем [Service] -> User на локального пользователя в /etc/systemd/system/stackdrive-headless.service
        
         [Unit]
         Description=StackDrive
         After=syslog.target
        
         [Service]
         User=*LOCAL_USER*
         ExecStart=/opt/stackdrive-headless/stackdrive-headless.jar
         SuccessExitStatus=143
        
         [Install]
         WantedBy=multi-user.target
        
### Активируем новый сервис в системе

        sudo systemctl enable stackdrive-headless.service
        
### Закидываем в /opt/stackdrive-headless настройки сервиса
        
        cp ./application.properties /opt/stackdrive-headless
        
### Запускаем новый сервис в системе

        sudo systemctl start stackdrive-headless
