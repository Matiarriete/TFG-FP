package com.example.demo.exceptions;

public class ResourceNotFoundException extends RuntimeException{
        public ResourceNotFoundException() {
            super("Resource not found");
        }
}
