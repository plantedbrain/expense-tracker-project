package com.example.expense.service;

import com.example.expense.model.Expense;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.sns.*;
import software.amazon.awssdk.services.sns.model.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final DynamoDbClient dynamo;
    private final SnsClient sns;

    @Value("${aws.dynamodb.table}")
    String table;

    @Value("${aws.sns.topicArn}")
    String topic;

    public Expense create(Expense e) {
        e.setExpenseId(UUID.randomUUID().toString());
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("expenseId", AttributeValue.builder().s(e.getExpenseId()).build());
        item.put("title", AttributeValue.builder().s(e.getTitle()).build());
        item.put("amount", AttributeValue.builder().n(e.getAmount().toString()).build());
        item.put("date", AttributeValue.builder().s(e.getDate()).build());
        dynamo.putItem(PutItemRequest.builder().tableName(table).item(item).build());
        //sns.publish(PublishRequest.builder().topicArn(topic).message("New expense: "+e.getTitle()).build());
        return e;
    }

    public List<Expense> getAll() {

        ScanRequest scanRequest = ScanRequest.builder()
                .tableName(table)
                .build();

        ScanResponse response = dynamo.scan(scanRequest);

        List<Expense> expenses = new ArrayList<>();

        for (Map<String, AttributeValue> item : response.items()) {
            Expense e = new Expense();
            e.setExpenseId(item.get("expenseId").s());
            e.setTitle(item.get("title").s());
            e.setAmount(Double.valueOf(item.get("amount").n()));
            e.setDate(item.get("date").s());
            expenses.add(e);
        }

     return expenses;
    }

    public void deleteById(String expenseId) {

     Map<String, AttributeValue> key = new HashMap<>();
     key.put("expenseId", AttributeValue.builder().s(expenseId).build());

     DeleteItemRequest request = DeleteItemRequest.builder()
             .tableName(table)
             .key(key)
             .build();

     dynamo.deleteItem(request);
    }

}
