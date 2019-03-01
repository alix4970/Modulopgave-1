package com.modulopgave1.util;

public class Console {
    private Console() {}

    public final static void clearConsole()
    {
        try
        {
            final String OS = System.getProperty("os.name");

            // if 'OS' is 'Windows'
            if (OS.contains("Windows"))
            {
                // build process inside this process
                // and wait for it to execute 'cls' i.e. 'clear screen'
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else // 'Linux' & 'Linux Kernel based' operating systems
            {
                // print ASCII code for 'clear console' & 'Home' (Home is to scroll up)
                System.out.print("\033[H\033[2J");
            }
        }
        catch (final Exception e)
        {
            System.out.println("Console was not cleared properly, something went terribly wrong.!\n");
            System.out.println(e.getMessage());
            System.out.println();
        }
        finally {
            // flush buffered output
            System.out.flush();
        }
    }
}
