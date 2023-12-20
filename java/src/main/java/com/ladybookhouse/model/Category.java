package com.ladybookhouse.model;

public class Category {


    //this type is about the category of books
    private String type;

    //new and used books
    private String condition;


    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition(Book book) {
        if(book.getCondition() == 11){
            condition = "NEW";
        }else {
            condition = "USED";
        }
        return condition;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBookTypes(String sku){
        String result = "";

        if(sku.startsWith("AM")){
            result += "Art";
        }else if(sku.startsWith("B")){
            result+= "Bestseller";
        }else if(sku.startsWith("D")){
            result+= "Design";
        }else if(sku.startsWith("FL")){
            result += "Literature";
        }else if(sku.startsWith("SS")){
            result += "Social Science";
        }else if(sku.startsWith("NS")){
            result += "Science";
        }else if(sku.startsWith("CS")){
            result+= "Technology";
        }else if(sku.startsWith("RP")){
            result+= "Religion&Philosophy";
        }else if(sku.startsWith("MK")){
            result+= "Kids";
        }else if(sku.startsWith("ML")){
            result+= "Language";
        }else if(sku.startsWith("HM")){
            result+= "History&Memory";
        }else if(sku.startsWith("TC")){
            result+= "Cookbook";
        }else{
            result+= "Other";
        }
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "type='" + type + '\'' +
                '}';
    }
}
