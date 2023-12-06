package com.ladybookhouse.model;

public class Category {

    private String type;

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
            result+= "Religion/Philosophy";
        }else if(sku.startsWith("MK")){
            result+= "Kids";
        }else if(sku.startsWith("ML")){
            result+= "Language";
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
