package uml;

import com.generalassembly.uml.classes.Asset;

import java.io.IOException;

public interface Market {
    void bookTrade(Asset asset) throws IOException;
}
