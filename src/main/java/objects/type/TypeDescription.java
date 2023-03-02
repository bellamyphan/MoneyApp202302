package objects.type;

public class TypeDescription {
    private final String description;

    public TypeDescription(Type type) {
        switch (type) {
            case INCOME_EARN -> description = "Earned income includes wages, salary, tips and commissions";
            case INCOME_PASSIVE -> description = "Passive or unearned income could come from rental properties, " +
                    "royalties and limited partnerships";
            case INCOME_PORTFOLIO -> description = "Portfolio or investment income includes interest, " +
                    "dividends and capital gains on investments";
            case TAX_EARN -> description = "Individual income taxes, corporate income taxes, payroll taxes, " +
                    "and capital gains taxes";
            case TAX_BUY -> description = "Sales taxes, gross receipts taxes, value-added taxes, and excise taxes";
            case TAX_OWN -> description = "Property taxes, tangible personal property taxes, " +
                    "estate and inheritance taxes, and wealth taxes";
            case INVEST_STOCK -> description = "401k Traditional and Roth, IRA Traditional and Roth, HSA, " +
                    "Brokerage account";
            case INVEST_REAL_ESTATE -> description = "Housing and Rental Properties investments";
            case INVEST_SAVING -> description = "Emergency saving, Vacation saving, Tax saving";
            case INVEST_OTHER -> description = "Life insurance";
            case HEALTH_DOCTOR -> description = "Primary doctor visits, specialist sessions, ER visits, " +
                    "hospitalization visits";
            case HEALTH_DENTAL -> description = "Dental care and orthodontics";
            case HEALTH_PHARMACY -> description = "Medicine subscription";
            case HEALTH_OTHER -> description = "Cleaning body supplies, lotion, beauties services like haircuts " +
                    "and nails and gym membership";
            case EDUCATION -> description = "Tuition, textbook, student loans, online courses, studying";
            case WORK -> description = "Working expenses and services needed";
            case CAR_TRANSPORTATION -> description = "Car payment, car insurance, car maintenance, " +
                    "car registration and related government fees";
            case CAR_GAS_TOLL -> description = "Gasoline, toll road and fees, parking fees";
            case GROCERY -> description = "Fresh food, frozen food, dairy milk eggs, fruits and cooking sauces";
            case GOVERNMENT -> description = "Government services like ID, passport, citizenship";
            case DINING -> description = "Restaurant food and drink";
            case HOUSING -> description = "Rent or mortgage payment";
            case UTILITY -> description = "Housing utilities like water, electricity, internet, trash, mails, phone, " +
                    "TV lines, smart home monthly fees";
            case PET -> description = "Food and VET cares, toys, treats and pet medicines";
            case ENTERTAINMENT -> description = "Entertaining services, movies, video games, music, theme parks, " +
                    "water parks";
            case SHOP_ELECTRONICS -> description = "Personal electronic devices like phone, laptop, airpods, speakers";
            case SHOP_HOUSE -> description = "Furniture, housing appliances, TV, cleaning supplies, lawn tools, " +
                    "home tools";
            case SHOP_OTHER -> description = "Misc";
            case CLOTHES -> description = "Shirt, pants, jacket, underwear, shoes, socks";
            case CHARITY -> description = "Gifts, monthly support to family";
            case CURVE -> description = "Curve the number with a bank balance for unknown errors and misinformation";
            case OTHER_TRANSFER -> description = "Transfer money between bank accounts";
            case OTHER_CREDIT_PAYMENT -> description = "Make a payment to a credit card";
            case OTHER -> description = "Transactions that cannot be sorted into any other types";
            default -> description = "No description available for this type '" + type +  "'";
        }
    }

    public String getDescription() {
        return description;
    }
}
