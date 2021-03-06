package com.fenyx.ui;

public class UIItemPool {

    private UI[] e;
    private UI[] e_tmp;

    private int size = 0;
    private int initialSize = 10;

    public UIItemPool() {
        this.e = new UI[this.initialSize];
        this.e_tmp = new UI[this.initialSize];
    }

    public UIItemPool(int initial) {
        if (initial > 0) {
            this.e = new UI[initial];
            this.e_tmp = new UI[initial];
        } else {
            this.e = new UI[this.initialSize];
            this.e_tmp = new UI[this.initialSize];
        }
    }

    public void add(UI element) {
        if (contains(element)) {
            return;
        }
        this.size += 1;

        if (this.size > this.e.length - 1) {
            UI[] tmp = new UI[this.size + this.initialSize];
            System.arraycopy(this.e, 0, tmp, 0, this.e.length);
            this.e = tmp;
        }

        this.e[(this.size - 1)] = element;
    }

    public void add(UI[] elements) {
        for (UI tmp : elements)
            add(tmp);
    }

    public void put(UI element, int pos) {
        if (pos > this.size - 1) {
            add(element);
        } else
            this.e[pos] = element;
    }

    public void swap(int i, int i2) {
        UI tmp = get(i);
        this.e[i] = this.e[i2];
        this.e[i2] = tmp;
    }

    public UI get(int i) {
        if (i > this.size - 1)
            return null;
        return this.e[i];
    }

    public UI[] get_all() {
        if (this.e_tmp.length != this.size) {
            this.e_tmp = new UI[this.size];
        }
        System.arraycopy(this.e, 0, this.e_tmp, 0, this.size);

        return this.e_tmp;
    }

    public UI get_last() {
        return get(this.size - 1);
    }

    public UI pop() {
        UI t = get_last();
        UI[] tmp = new UI[this.e.length - 1];
        System.arraycopy(this.e, 0, tmp, 0, this.e.length - 1);
        this.e = tmp;

        return t;
    }

    public void remove(int i) {
        this.e[i] = null;

        UI[] tmp = new UI[this.e.length - 1];
        System.arraycopy(this.e, 0, tmp, 0, i);
        System.arraycopy(this.e, i + 1, tmp, i, this.e.length - (i + 1));
        this.e = tmp;

        this.size -= 1;
    }

    public void remove(UI element) {
        int i = 0;

        for (UI a : this.e) {
            if (a == element) {
                remove(i);
                return;
            }

            i++;
        }
    }

    public void clear() {
        this.e = new UI[this.initialSize];
        this.e_tmp = new UI[this.initialSize];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean is_empty() {
        return this.size == 0;
    }

    public boolean contains(UI entity) {
        for (UI tmp : this.e)
            if (tmp == entity)
                return true;
        return false;
    }

    public void invert() {
        for (int i = 0; i < this.e.length; i++) {
            UI temp = this.e[i];
            this.e[i] = this.e[(this.e.length - i - 1)];
            this.e[(this.e.length - i - 1)] = temp;
        }
    }
}