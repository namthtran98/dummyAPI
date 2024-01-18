package com.example.demo.until;

import com.example.demo.products.entities.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Untils {
        public static Iterable<Product> limitArray(Iterable<Product> arr, int limit) {
            long length = StreamSupport.stream(arr.spliterator(), false).count();
            if (limit == 0 || limit > length) {
                return arr;
            } else {
                return StreamSupport.stream(arr.spliterator(), false).limit(limit).collect(Collectors.toList());
            }
        }

        public static Iterable<Product> sliceArray(Iterable<Product> arr, int endIndex) {
            List<Product> productList = StreamSupport.stream(arr.spliterator(), false).collect(Collectors.toList());
            List<Product> result = productList.stream().skip(endIndex).collect(Collectors.toList());
            return result;
        }

    private static Product getObjectSubset(Product obj, List<String> keys) {
        Product subset = new Product();
        subset.setId(obj.getId());
        for (String key : keys) {
            switch (key) {
                case "title":
                    subset.setTitle(obj.getTitle());
                    break;
                case "description":
                    subset.setDescription(obj.getDescription());
                    break;
                case "price":
                    subset.setPrice(obj.getPrice());
                    break;
                case "discountpercentage":
                    subset.setDiscountpercentage(obj.getDiscountpercentage());
                    break;
                case "rating":
                    subset.setRating(obj.getRating());
                    break;
                case "stock":
                    subset.setStock(obj.getStock());
                    break;
                case "brand":
                    subset.setBrand(obj.getBrand());
                    break;
                case "category":
                    subset.setCategory( obj.getCategory());
                    break;
                case "thumbnail":
                    subset.setThumbnail(obj.getThumbnail());
                    break;
                case "images":
                    subset.setImages(obj.getImages());
                    break;
            }
        }

        return subset;
    }

    public static Iterable<Product> getMultiObjectSubset(Iterable<Product> arr, List<String> keys) {
        return StreamSupport.stream(arr.spliterator(), false)
                .map(p -> getObjectSubset(p, keys))
                .collect(Collectors.toList());
    }

    public static int getSize(Iterable<Product> arr) {
        var total = 0;
        for (Product product : arr) total++;
        return total;
    }
}
