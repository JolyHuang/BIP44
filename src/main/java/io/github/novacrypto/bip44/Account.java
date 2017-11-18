/*
 *  BIP44
 *  Copyright (C) 2017 Alan Evans, NovaCrypto
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *  Original source: https://github.com/NovaCrypto/BIP44
 *  You can contact the authors via github issues.
 */

package io.github.novacrypto.bip44;

import static io.github.novacrypto.bip32.Index.hardened;

public final class Account {
    private final CoinType coinType;
    private final int account;
    private final String string;

    Account(final CoinType coinType, final int account) {
        if (hardened(account))
            throw new IllegalArgumentException();
        this.coinType = coinType;
        this.account = account;
        string = String.format("%s/%d'", coinType, account);
    }

    int getValue() {
        return account;
    }

    CoinType getParent() {
        return coinType;
    }

    @Override
    public String toString() {
        return string;
    }

    public Change external() {
        return new Change(this, 0);
    }

    public Change internal() {
        return new Change(this, 1);
    }
}