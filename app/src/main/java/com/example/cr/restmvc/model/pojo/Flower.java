package com.example.cr.restmvc.model.pojo;

public class Flower
{
    public String category, instructions, photo, name;
    public double price;
    public int productId;

    public Flower(Builderm builder)
    {
        category = builder.category;
        instructions = builder.instructions;
        photo = builder.photo;
        name = builder.name;
        price = builder.price;
        productId = builder.productId;
    }

    public static class Builderm
    {
        private String category, instructions, photo, name;
        private double price;
        private int productId;

        public Builderm setCategory(String category)
        {
            this.category = category;
            return Builderm.this;
        }

        public Builderm setInstructions(String instructions)
        {
            this.instructions = instructions;
            return Builderm.this;
        }

        public Builderm setPhoto(String photo)
        {
            this.photo = photo;
            return Builderm.this;
        }

        public Builderm setName(String name)
        {
            this.name = name;
            return Builderm.this;
        }

        public Builderm setPrice(double price)
        {
            this.price = price;
            return Builderm.this;
        }

        public Builderm setProductId(int productId)
        {
            this.productId = productId;
            return Builderm.this;
        }

        public Flower build()
        {
            return new Flower(Builderm.this);
        }
    }
}
