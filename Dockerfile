FROM openjdk:22-jdk
ADD target/TodoApp.jar rest-todo.jar

ENTRYPOINT ["java", "-jar","/rest-todo.jar"]