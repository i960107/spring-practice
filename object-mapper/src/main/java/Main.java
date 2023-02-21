import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("main");

        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setName("홍길동");
        user.setAge(16);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("세단");

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("SUV");

        List<Car> cars = Arrays.asList(car1, car2);
        user.setCars(cars);

//        System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
//        System.out.println(json);
        JsonNode jsonNode = objectMapper.readTree(json);
        //미리 type, name을 알 수 있을 때 사용 가능
        String name = jsonNode.get("name").asText();
        int age = jsonNode.get("age").asInt();
        System.out.println("name : " + name);
        System.out.println("age : " + age);

        // 배열 노드를 어떻게 받을 것인가. 노드 자체가 배열
        ArrayNode cars_node = (ArrayNode) jsonNode.get("cars");
        //Object를 Type Reference 지정한 에 우리가 받고자하는 GenericType으로 변환.
        List<Car> _cars = objectMapper.convertValue(cars_node, new TypeReference<List<Car>>() {
        });
        System.out.println("Cars :" + _cars);

        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "이슬");
        objectNode.put("age", 16);
        System.out.println(objectNode.toPrettyString());
    }
}
