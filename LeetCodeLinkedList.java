import java.util.*;

public class LeetCodeLinkedList {

    public static void main(String[] args) {
        RandomListNode node = new RandomListNode(1, new RandomListNode(2, null, new RandomListNode(1)), new RandomListNode(1));
        printRandomLinkedList(node, ",");
        RandomListNode result = copyRandomList(node);
        printRandomLinkedList(result, ",");
    }

    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;

        RandomListNode node = head;
        RandomListNode newHead = new RandomListNode(0);
        RandomListNode newNode = newHead;

        while (node != null) {
            newNode.val = node.val;
            newNode.next = node.next;
            node.next = newNode;
            node = newNode.next;
            if (node != null) {
                newNode = new RandomListNode(0);
            }
        }

        node = head;
        newNode = newHead;
        while (node != null) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
            node = node.next.next;
        }

        node = head;
        newNode = newHead;
        while (node != null) {
            node.next = newNode.next;
            if (newNode.next != null) {
                newNode.next = newNode.next.next;
            }
            node = node.next;
            newNode = newNode.next;
        }

        return newHead;
    }

    public static class RandomListNode {
        public int val;
        public RandomListNode next;
        public RandomListNode random;

        public RandomListNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public RandomListNode(int val, RandomListNode next, RandomListNode random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }

    public static void printRandomLinkedList(RandomListNode node, String sep) {
        System.out.println();
        while (node != null) {
            System.out.print(String.valueOf(node.val));
            if (node.random != null) {
                System.out.print(sep + String.valueOf(node.random.val));
            }
            node = node.next;
            if (node != null) {
                System.out.print(sep);
            }
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        //getting length
        int length = 1;
        while (head.next != null) {
            head = head.next;
            length++;
        }

        head.next = dummy.next;
        dummy = head;

        while ((k % length) != 0) {
            k++;
            dummy = dummy.next;
        }

        head = dummy.next;
        dummy.next = null;
        return head;
    }

    public static DoubleChildListNode flatten(DoubleChildListNode head) {
        if (head == null)
            return head;

        DoubleChildListNode current = head;
        DoubleChildListNode tail;
        while (current != null) {
            if (current.child != null) {
                tail = current.child;

                while (tail.next != null)
                    tail = tail.next;

                tail.next = current.next;

                if (current.next != null)
                    current.next.prev = tail;

                current.next = current.child;
                current.child.prev = current;
                current.child = null;
            }
            current = current.next;
        }
        return head;
    }

    public static class DoubleChildListNode {
        public int value;
        public DoubleChildListNode prev;
        public DoubleChildListNode next;
        public DoubleChildListNode child;

        public DoubleChildListNode(int value) {
            this.value = value;
            this.prev = null;
            this.next = null;
            this.child = null;
        }

        public DoubleChildListNode(int value, DoubleChildListNode prev, DoubleChildListNode next, DoubleChildListNode child) {
            this.value = value;
            this.prev = prev;
            this.next = next;
            this.child = child;
        }
    }

    public static void printDoubleChildLinkedList(DoubleChildListNode node, String sep) {
        System.out.println();
        while (node != null) {
            System.out.print(String.valueOf(node.value));
            if(node.child != null) {
                while(node.child != null) {
                    System.out.print(sep + String.valueOf(node.child.value));
                    node.child = node.child.next;
                    if (node.child != null) {
                        System.out.print(sep);
                    }
                }
            }
            node = node.next;
            if (node != null) {
                System.out.print(sep);
            }
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        ListNode p = l1;
        ListNode q = l2;

        while (p != null || q != null) {
            int x = p == null ? 0 : p.value;
            int y = q == null ? 0 : q.value;
            int sum = x + y + carry;
            carry = sum / 10;

            current.next = new ListNode(sum % 10);
            current = current.next;
            if (p != null)
                p = p.next;
            if (q != null)
                q = q.next;
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return dummy.next;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (true) {
            // l1 node reaches to its end assign tail next to l2 and return
            if (l1 == null) {
                tail.next = l2;
                break;
            }

            // l2 node reaches to its end assign tail next to l1 and return
            if (l2 == null) {
                tail.next = l1;
                break;
            }

            // check and compare l1 and l2 values for sort and merge
            if (l1.value <= l2.value) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }

            // advance the tail node
            tail = tail.next;
        }

        return dummy.next;
    }

    public static void designedDoubleLinkedList() {
        MyDoubleLinkedList obj = new MyDoubleLinkedList();

        obj.addAtHead(7);
        obj.addAtHead(2);
        obj.addAtHead(1);
        printDoubleLinkedList(obj.mNodeHead, ",");

        obj.addAtIndex(3, 0);
        printDoubleLinkedList(obj.mNodeHead, ",");

        obj.deleteAtIndex(2);
        printDoubleLinkedList(obj.mNodeHead, ",");

        obj.addAtHead(6);
        printDoubleLinkedList(obj.mNodeHead, ",");

        obj.addAtTail(4);
        printDoubleLinkedList(obj.mNodeHead, ",");

        System.out.println("\n" + obj.get(4));
        ;

        obj.addAtHead(4);
        printDoubleLinkedList(obj.mNodeHead, ",");

        obj.addAtIndex(5, 0);
        printDoubleLinkedList(obj.mNodeHead, ",");

        obj.addAtHead(6);
        printDoubleLinkedList(obj.mNodeHead, ",");
    }

    public static class MyDoubleLinkedList {
        public DoubleListNode mNodeHead;

        /** Initialize your data structure here. */
        public MyDoubleLinkedList() {

        }

        /**
         * Get the value of the index-th node in the linked list. If the index is
         * invalid, return -1.
         */
        public int get(int index) {
            if (!isEmpty()) {
                int i = 0;
                DoubleListNode current = mNodeHead;
                while (current != null) {
                    if (index == i)
                        return current.value;
                    current = current.next;
                    i++;
                }
            }
            return -1;
        }

        /**
         * Add a node of value val before the first element of the linked list. After
         * the insertion, the new node will be the first node of the linked list.
         */
        public void addAtHead(int val) {
            if (!isEmpty()) {
                DoubleListNode newNode = new DoubleListNode(val);
                newNode.next = mNodeHead;

                mNodeHead.prev = newNode;
                mNodeHead = newNode;
            } else {
                mNodeHead = new DoubleListNode(val);
            }
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            if (!isEmpty()) {
                DoubleListNode newNode = new DoubleListNode(val);

                DoubleListNode current = mNodeHead;
                outerloop: while (true) {
                    if (current.next == null)
                        break outerloop;
                    current = current.next;
                }

                newNode.prev = current;
                current.next = newNode;
            } else {
                mNodeHead = new DoubleListNode(val);
            }
        }

        /**
         * Add a node of value val before the index-th node in the linked list. If index
         * equals to the length of linked list, the node will be appended to the end of
         * linked list. If index is greater than the length, the node will not be
         * inserted.
         */
        public void addAtIndex(int index, int val) {
            if (!isEmpty()) {
                int size = length();
                if (index > size)
                    return;

                if (index == size) {
                    addAtTail(val);
                    return;
                }

                if (index == 0) {
                    addAtHead(val);
                    return;
                }

                int i = 0;
                DoubleListNode current = mNodeHead;
                DoubleListNode last = mNodeHead;
                while (i != index) {
                    current = last;
                    last = current.next;
                    i++;
                }
                DoubleListNode newNode = new DoubleListNode(val, current, last);
                current.next = newNode;
                last.prev = newNode;
            } else {
                mNodeHead = new DoubleListNode(val);
            }
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            int size = length() - 1;
            if (isEmpty() || index > size)
                return;

            if (index == 0 && size == 0) {
                mNodeHead = null;
                return;
            }

            if (index == 0) {
                mNodeHead = mNodeHead.next;
                mNodeHead.prev = null;
                return;
            }

            int i = 0;
            DoubleListNode current = mNodeHead;
            while (current != null) {
                i++;
                if (index == i)
                    break;
                current = current.next;
            }
            if (index == size) {
                current.next = null;
            } else {
                current.next.next.prev = current;
                current.next = current.next.next;
            }
        }

        public boolean isEmpty() {
            return length() == 0;
        }

        public int length() {
            if (mNodeHead != null) {
                int i = 0;
                DoubleListNode current = mNodeHead;
                while (current != null) {
                    current = current.next;
                    i++;
                }
                return i;
            }
            return 0;
        }
    }

    public static class DoubleListNode {
        public int value;
        public DoubleListNode prev;
        public DoubleListNode next;

        public DoubleListNode(int value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }

        public DoubleListNode(int value, DoubleListNode prev, DoubleListNode next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void printDoubleLinkedList(DoubleListNode node, String sep) {
        System.out.println();
        while (node != null) {
            System.out.print(String.valueOf(node.value));
            node = node.next;
            if (node != null) {
                System.out.print(sep);
            }
        }
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode current = head;
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }

        // find the middle node
        int count = 1;
        current = head;
        while (count < size / 2 && current != null) {
            count++;
            current = current.next;
        }

        // reverse right node
        ListNode rightNode = current;
        ListNode prev = null;
        ListNode curr = rightNode;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        // compare left node with right node
        ListNode headRight = prev;
        ListNode headLeft = head;
        while (headLeft != null && headRight != null) {
            if (!(headLeft.value == headRight.value)) {
                return false;
            } else {
                headLeft = headLeft.next;
                headRight = headRight.next;
            }
        }

        return true;
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null)
            return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        ListNode last = new ListNode(69);
        last.next = head;
        ListNode sudoHead = last;
        while (head != null) {
            if (head.value == val) {
                last.next = head.next;
            } else {
                last = last.next;
            }
            head = head.next;
        }
        return sudoHead.next;
    }

    public static ListNode reverseListIteration(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }
        return previous;
    }

    public static ListNode reversListRecursion(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode p = reversListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null)
            return null;

        int length = 0;
        int index = 0;
        ListNode headA = head;
        ListNode headB = head;

        while (headA != null) {
            length++;
            headA = headA.next;
        }

        index = length - n;

        if (index == 0) {
            head = head.next;
            return head;
        }

        int count = 0;
        while (headB != null) {
            count++;
            if (index == count)
                break;
            headB = headB.next;
        }

        if (index == length) {
            headB.next = null;
        } else {
            headB.next = headB.next.next;
        }

        return head;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;

        ListNode firstNode = headA;
        ListNode secondNode = headB;

        int firstListLength = 0;
        int secondListLength = 0;

        while (firstNode != null) {
            firstListLength++;
            firstNode = firstNode.next;
        }

        while (secondNode != null) {
            secondListLength++;
            secondNode = secondNode.next;
        }

        firstNode = headA;
        secondNode = headB;

        if (firstListLength > secondListLength) {
            int diff = firstListLength - secondListLength;
            for (int i = 0; i < diff; i++) {
                firstNode = firstNode.next;
            }
        } else {
            int diff = secondListLength - firstListLength;
            for (int i = 0; i < diff; i++) {
                secondNode = secondNode.next;
            }
        }

        while (firstNode != secondNode) {
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }
        return firstNode;
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slowPointer = head.next;
        ListNode fastPointer = head.next.next;

        while (slowPointer != fastPointer) {
            if (slowPointer == null || fastPointer == null)
                return null;

            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;

            if (fastPointer != null) {
                fastPointer = fastPointer.next;
            } else {
                return null;
            }
        }

        fastPointer = head;
        while (slowPointer != fastPointer) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }

        return slowPointer;
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slowPointer = head;
        ListNode fastPointer = head.next;

        while (slowPointer != fastPointer) {
            if (fastPointer == null || fastPointer.next == null)
                return false;

            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        return true;
    }

    public static void designedSingleLinkedList() {
        MyLinkedList obj = new MyLinkedList();
        obj.get(0);
        obj.addAtHead(1);
        printSingleLinkedList(obj.mNodeHead, ",");
        obj.addAtTail(3);
        printSingleLinkedList(obj.mNodeHead, ",");
        obj.addAtIndex(1, 2);
        obj.addAtIndex(1, 4);
        printSingleLinkedList(obj.mNodeHead, ",");
        obj.deleteAtIndex(2);
        obj.get(1);
        printSingleLinkedList(obj.mNodeHead, ",");
    }

    public static class MyLinkedList {
        public ListNode mNodeHead;

        /** Initialize your data structure here. */
        public MyLinkedList() {

        }

        /**
         * Get the value of the index-th node in the linked list. If the index is
         * invalid, return -1.
         */
        public int get(int index) {
            if (!isEmpty()) {
                int i = 0;
                ListNode current = mNodeHead;
                while (current != null) {
                    if (index == i)
                        return current.value;
                    current = current.next;
                    i++;
                }
            }
            return -1;
        }

        /**
         * Add a node of value val before the first element of the linked list. After
         * the insertion, the new node will be the first node of the linked list.
         */
        public void addAtHead(int val) {
            if (!isEmpty()) {
                ListNode next = mNodeHead;
                ListNode newNode = new ListNode(val, next);
                mNodeHead = newNode;
            } else {
                mNodeHead = new ListNode(val);
            }
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            if (!isEmpty()) {
                ListNode newNode = new ListNode(val);

                ListNode current = mNodeHead;
                outerloop: while (true) {
                    if (current.next == null)
                        break outerloop;
                    current = current.next;
                }

                current.next = newNode;
            } else {
                mNodeHead = new ListNode(val);
            }
        }

        /**
         * Add a node of value val before the index-th node in the linked list. If index
         * equals to the length of linked list, the node will be appended to the end of
         * linked list. If index is greater than the length, the node will not be
         * inserted.
         */
        public void addAtIndex(int index, int val) {
            if (!isEmpty()) {
                int size = length();
                if (index > size)
                    return;

                if (index == size) {
                    addAtTail(val);
                    return;
                }

                if (index == 0) {
                    addAtHead(val);
                    return;
                }

                int i = 0;
                ListNode current = mNodeHead;
                ListNode last = mNodeHead;
                while (i != index) {
                    current = last;
                    last = current.next;
                    i++;
                }
                ListNode newNode = new ListNode(val, last);
                current.next = newNode;
            } else {
                mNodeHead = new ListNode(val);
            }
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            int size = length() - 1;
            if (isEmpty() || index > size)
                return;

            if (index == 0 && size == 0) {
                mNodeHead = null;
                return;
            }

            if (index == 0) {
                mNodeHead = mNodeHead.next;
                return;
            }

            int i = 0;
            ListNode current = mNodeHead;
            while (current != null) {
                i++;
                if (index == i)
                    break;
                current = current.next;
            }
            if (index == size) {
                current.next = null;
            } else {
                current.next = current.next.next;
            }
        }

        public boolean isEmpty() {
            return length() == 0;
        }

        public int length() {
            if (mNodeHead != null) {
                int i = 0;
                ListNode current = mNodeHead;
                while (current != null) {
                    current = current.next;
                    i++;
                }
                return i;
            }
            return 0;
        }
    }

    public static class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            this.next = null;
        }

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void printSingleLinkedList(ListNode node, String sep) {
        System.out.println();
        while (node != null) {
            System.out.print(String.valueOf(node.value));
            node = node.next;
            if (node != null) {
                System.out.print(sep);
            }
        }
    }
}