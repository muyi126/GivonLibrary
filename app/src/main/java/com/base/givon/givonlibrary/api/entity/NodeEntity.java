package com.base.givon.givonlibrary.api.entity;


import com.base.givon.givonlibrary.api.entity.element.Node;

import java.util.List;

public class NodeEntity {

    public class Nodes {
        public List<Node> data;

        public List<Node> getData() {
            return data;
        }

        public void setData(List<Node> data) {
            this.data = data;
        }
    }

    public class ANode {
        public Node data;

        public Node getData() {
            return data;
        }

        public void setData(Node data) {
            this.data = data;
        }
    }

}
