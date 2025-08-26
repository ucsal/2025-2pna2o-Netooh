package br.com.mariojp.solid.ocp;

import java.util.EnumMap;
import java.util.Map;

public class DiscountCalculator {

    private final Map<CustomerType, DiscountPolicy> policies;
    private final DiscountPolicy defaultPolicy;

    public DiscountCalculator() {
        this.policies = new EnumMap<>(CustomerType.class);
        this.policies.put(CustomerType.REGULAR, new RegularPolicy());
        this.policies.put(CustomerType.PREMIUM, new PremiumPolicy());
        this.policies.put(CustomerType.PARTNER, new PartnerPolicy());
        this.defaultPolicy = amount -> amount;
    }

    public DiscountCalculator(Map<CustomerType, DiscountPolicy> policies) {
        this(policies, amount -> amount);
    }

    public DiscountCalculator(Map<CustomerType, DiscountPolicy> policies, DiscountPolicy defaultPolicy) {
        this.policies = policies;
        this.defaultPolicy = defaultPolicy;
    }

    public double apply(double amount, CustomerType type) {
        return policies.getOrDefault(type, defaultPolicy).apply(amount);
    }
}
