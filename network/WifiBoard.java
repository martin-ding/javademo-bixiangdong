package network;

class WifiBoard implements PCI
{
    public void open()
    {
        System.out.println("WifiBoard is opening .....");
    }

    public void close()
    {
        System.out.println("WifiBoard is closing .....");
    }
}