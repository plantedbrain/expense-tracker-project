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

@Service @RequiredArgsConstructor
public class ExpenseService {
 private final DynamoDbClient dynamo;
 private final SnsClient sns;

 @Value("${aws.dynamodb.table}") String table;
 @Value("${aws.sns.topicArn}") String topic;

 public Expense create(Expense e){
  e.setExpenseId(UUID.randomUUID().toString());
  Map<String,AttributeValue> item=new HashMap<>();
  item.put("expenseId",AttributeValue.builder().s(e.getExpenseId()).build());
  item.put("title",AttributeValue.builder().s(e.getTitle()).build());
  item.put("amount",AttributeValue.builder().n(e.getAmount().toString()).build());
  item.put("date",AttributeValue.builder().s(e.getDate()).build());
  dynamo.putItem(PutItemRequest.builder().tableName(table).item(item).build());
  sns.publish(PublishRequest.builder().topicArn(topic).message("New expense: "+e.getTitle()).build());
  return e;
 }
}
