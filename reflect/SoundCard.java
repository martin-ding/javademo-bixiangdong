package reflect;

class SoundCard implements IPCI
{
    public void open()
    {
        System.out.println("sound card open");
    }

    public void close()
    {
        System.out.println("sound card close");
    }
}