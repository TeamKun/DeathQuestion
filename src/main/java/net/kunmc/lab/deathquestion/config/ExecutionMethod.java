package net.kunmc.lab.deathquestion.config;

import java.util.Arrays;

public enum ExecutionMethod {
    KILL("kill"),
    CHANGE_TO_SPECTATOR("changeToSpectator");


    public static final String PATH = "executionMethod";
    private String name;

    ExecutionMethod(String name) {
        this.name = name;
    }

    /**
     * 名前を取得する
     * */
    public String methodName() {
        return this.name;
    }

    /**
     * 引き当てる
     * */
    public static ExecutionMethod getMethodName(String methodName) {
        return Arrays.stream(ExecutionMethod.values())
                .filter(name -> name.methodName().equalsIgnoreCase(methodName))
                .findFirst()
                .orElse(ExecutionMethod.CHANGE_TO_SPECTATOR);
    }
}
