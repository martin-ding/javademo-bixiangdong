class SingleDemo
{
    public static SingleDemo instance;
    protected String name;
    protected int age;
    private boolean hasSetflag = false;

    private SingleDemo()
    {

    }

    public static SingleDemo getInstance()
    {
        if (instance == null)
        {
            synchronized(SingleDemo.class) 
            {
                if(instance == null) {
                    instance = new SingleDemo();
                }
            }
        }
        return instance; 
    }

    /*获取age*/
    public int getAge()
    {
        return this.age;
    }

    /*获取name*/
    public String getName()
    {
        return this.name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean getFlag()
    {
        return this.hasSetflag;
    }

    public void setFlag(boolean flag)
    {
        this.hasSetflag = flag;
    }
}

