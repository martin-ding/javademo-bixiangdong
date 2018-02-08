package reflect;

class WifiCard implements IPCI
{
    private void show()
    {
        System.out.println("show wifi....");
    }

    public void open()
    {
        show();
        System.out.println("Wifi is open ...");
    }

    public void close()
    {
        System.out.println("Wifi is close ...");
    }
}