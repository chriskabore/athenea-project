package com.beogotech.atheneaproject.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
	private T name;
	private List<TreeNode<T>> children = new ArrayList<>();
	private TreeNode parent = null;
	
	public TreeNode(T name){
		this.name = name;
	}
	
	public void addChild(TreeNode<T> child){
		child.setParent(this);
		this.children.add(child);
	}
	
	public void addChild(T name){
		TreeNode<T> child = new TreeNode<T>(name);
		addChild(this);
	}
	
	public void addChildren(List<TreeNode<T>>newChildren){
		newChildren.forEach(child->setParent(this));
		this.children.addAll(newChildren);
	}
	
	public List<TreeNode<T>> getChildren(){
		return this.children;
	}
	
	private void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}
	
	public TreeNode<T> getParent(){
		return this.parent;
	}
}
