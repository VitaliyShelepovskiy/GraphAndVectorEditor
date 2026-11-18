package com.example.vectoreditor_;
import java.util.ArrayDeque;
import java.util.Deque;
public class MemoSelect
{
    private final Deque<Memento> mementoList = new ArrayDeque<>();
    public void push(Memento state) { mementoList.push(state); }
    public Memento pop() { return mementoList.poll(); }


}
